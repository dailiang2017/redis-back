package com.dail.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: dailiang
 * @Date: 2018/12/29 17:36
 * @Description:
 */
@Slf4j
public class BeanUtil {

    public static <T> T copyProperties(Object o,T target){
        if(o == null) return null;
        BeanUtils.copyProperties(o,target);
        return target;
    }

    public static <T> T copyProperties(Object o){
        if(o == null) return null;
        T newObject = null;
        try {
            newObject = (T) o.getClass().newInstance();
            BeanUtils.copyProperties(o,newObject);
        } catch (InstantiationException e) {
            log.error("BeanUtil.copyProperties error", e);
        } catch (IllegalAccessException e) {
            log.error("BeanUtil.copyProperties error", e);
            log.error("",e);
        }
        return newObject;
    }

    public static <T> T copyProperties(Object o,Class<T> c){
        if(o == null) return null;
        T newObject = null;
        try {
            newObject = c.newInstance();
            BeanUtils.copyProperties(o,newObject);
        } catch (InstantiationException e) {
            log.error("BeanUtil.copyProperties error", e);
        } catch (IllegalAccessException e) {
            log.error("BeanUtil.copyProperties error", e);
            log.error("",e);
        }
        return newObject;
    }

    public static <T> List<T> copyPropertiesForList(List list, Class<T> c){
        List<T> result = new ArrayList<T>();
        if(CollectionUtils.isEmpty(list)) return result;
        for(Object o :list){
            result.add(copyProperties(o,c));
        }
        return result;
    }
}
