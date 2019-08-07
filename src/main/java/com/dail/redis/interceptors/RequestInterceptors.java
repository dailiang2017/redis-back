package com.dail.redis.interceptors;

import com.alibaba.fastjson.JSON;
import com.dail.redis.constants.CookieConstant;
import com.dail.redis.constants.PrefixConstant;
import com.dail.redis.constants.RedisConstant;
import com.dail.redis.enums.ErrorCodeEnum;
import com.dail.redis.results.BaseResult;
import com.dail.redis.results.CacheResult;
import com.dail.redis.results.TokenInfo;
import com.dail.redis.utils.CookieUtils;
import com.dail.redis.utils.RedisClient;
import com.dail.redis.utils.StringUtil;
import com.dail.redis.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @Auther: dailiang
 * @Date: 2018/12/27 17:38
 * @Description:
 */
@Slf4j
@Configuration
public class RequestInterceptors implements HandlerInterceptor {

    @Autowired
    private RedisClient redisClient;

    private String[] filter = new String[]{"/user/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println(request.getServletPath() + "进入拦截器");
        try {
            if (Arrays.asList(filter).contains(request.getServletPath())) {
                System.out.println("跳过拦截器");
                return true;
            }
            String paramToken = request.getParameter(CookieConstant.COOKIE_NAME_TOKEN);
            String cookieToken = CookieUtils.getCookieValue(request, CookieConstant.COOKIE_NAME_TOKEN);
            if (StringUtil.isBlankOrEmpty(paramToken) && StringUtil.isBlankOrEmpty(cookieToken)) {
                render(response, ErrorCodeEnum.UNAUTHORIZED);
                return false;
            }
            String token = StringUtil.isNotEmpty(paramToken) ? paramToken : cookieToken;
            CacheResult<TokenInfo> result = redisClient.get(PrefixConstant.TOKEN_KEY + token, TokenInfo.class);
            if (result.getData() == null) {
                render(response, ErrorCodeEnum.LOGIN_EXPIRE);
                return false;
            } else {
                // 存入ThreadLocal
                ThreadLocalUtil.setTokenInfo(result.getData());
                if (frequencyControl(result.getData())) {
                    render(response, ErrorCodeEnum.REQUEST_FREQUENT);
                    return false;
                }
                // 存入限频标记
                redisClient.set(PrefixConstant.FREQUENCY_KEY + result.getData().getId(), RedisConstant.frequencyToExpireDefault);
                // 延长token超时时间
                redisClient.exipre(PrefixConstant.TOKEN_KEY + token, RedisConstant.tokenToExpireDefault);
            }
        } catch (Exception e) {
            log.error("请求拦截发生异常", e.getMessage());
        } finally {
            System.out.println("离开拦截器");
        }
        return true;
    }

    /**
     * 控制同一个用户的访问频率(1秒)
     * @param tokenInfo
     * @return
     */
    private boolean frequencyControl(TokenInfo tokenInfo) {
        CacheResult<String> userName = redisClient.get(PrefixConstant.FREQUENCY_KEY + tokenInfo.getId(), String.class);
        if (userName.getData() == null) return false;
        return true;
    }

    /**
     * 返回
     * @param response
     * @param codeEnum
     * @throws Exception
     */
    private void render(HttpServletResponse response, ErrorCodeEnum codeEnum) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        if (codeEnum != null) {
            //配合前端使用
            response.setHeader("x-auth-token", codeEnum.getRemark());
        }
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(BaseResult.error(codeEnum.getRemark()));
        out.write(str.getBytes("UTF-8"));
        out.flush();
    }
}
