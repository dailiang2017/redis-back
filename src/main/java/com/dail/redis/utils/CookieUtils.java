package com.dail.redis.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: dailiang
 * @Date: 2018/12/28 11:22
 * @Description:
 */
public class CookieUtils {
    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param cookieName    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(cookieName)) {
            Cookie cookie = (Cookie) cookieMap.get(cookieName);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 根据名字获取cookie
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(cookieName)) {
            Cookie cookie = (Cookie) cookieMap.get(cookieName);
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 保存Cookies
     *
     * @param response servlet请求
     * @param value    保存值
     * @author jxf
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }

    /**
     * 删除cookie
     * @param response
     * @param name
     * @return
     */
    public static HttpServletResponse removeCookie(HttpServletResponse response, String name) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, null);
        // tomcat下多应用共享
        cookie.setPath("/");
        cookie.setMaxAge(0);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }
}
