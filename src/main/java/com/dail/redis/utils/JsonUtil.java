package com.dail.redis.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * json工具类. 使用 jackson 的 json 处理库
 * 
 * @author suyulin
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 把 java 对象转化为 json 对象
     */
    public static <T> String java2json(T t) {
        String json = null;
        try {
        	mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            json = mapper.writeValueAsString(t);
        } catch (JsonGenerationException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
    /**
     * 把 json 对象转化为 java 对象
     *
     * -----------------------------------------------------------------------
     * 说明(1):
     * 1. json 对象中的属性 在 java 对象中 必须存在, 否则会报错;
     * 2. java 对象中 可以 包含 json 对象中 没有的属性.
     *
     * 以上两点, 可以总结如下: json 对象中的属性列表 必须是 java 对象中的属性列表 的一个子集 (子集的特例是: 自己是自己的子集)
     * 
     * 用途: 把简单的 json 对象 转成 简单的 JavaBean 对象, 例如: Apple apple = JsonUtil.json2java(json, Apple.class);
     * -----------------------------------------------------------------------
     * 
     * 说明(2):
     * 如果 json 对象比较复杂, 可以先转成 Map 对象, 再操作 Map 对象.
     * 这种方式不太 OO, 但可以说是 "万能的" 转换方法
     * 
     * 用途: 把复杂的 json 对象 转成 Map 对象, 例如: Map<String, Object> map = JsonUtil.json2java(json, Map.class);
     * -----------------------------------------------------------------------
     *
     * 说明(3):
     * 如果 json 对象比较复杂, 也可以 转成 复杂的 JavaBean 对象.
     * 这种方式比较 OO, 但要求写一个复杂的 JavaBean 对象, 来与 json 对象 匹配
     *
     * 用途: 把复杂的 json 对象 转成 复杂的 JavaBean 对象, 例如: JsonExampleVo vo = JsonUtil.json2java(json, JsonExampleVo.class);
     *
     * 具体用法: 见 main() 方法, 以及 JsonExampleVo 类
     */
    public static <T> T json2java(String json, Class<T> valueType) {
        T obj = null;
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            obj = mapper.readValue(json, valueType);
        } catch (JsonGenerationException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    /**
     * 不能使用
     * @param json
     * @param valueType
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> json2javaList(String json,   Class<T> valueType){
        List<T> list=new ArrayList<>();
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            list = mapper.readValue(json,t.constructCollectionType(ArrayList.class,valueType));
        } catch (JsonGenerationException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static <T> Set<T> json2javaSet(String json,   Class<T> valueType){
        Set<T> set=new HashSet<>();
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            set = mapper.readValue(json,t.constructCollectionType(HashSet.class,valueType));
        } catch (JsonGenerationException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return set;
    }

    /**
     * test: 上面两个 "转换" 方法,
     * 主要是 test: 把复杂的 json 对象转化为 java 对象
     *
     * 这里, json 对象的格式是:
-------------------------------------------------------------------------
     {
	"code":200,
	"message":"Request processing succeeded",
	"header":{},
	"result":{
		"totalCount":66,
		"pageNum":1,
		"pageSize":2,
		"bookList":[
		  {"name":"Spring MVC 3.0","author":"Rod Johnson","page":180,"price":34.99},
		  {"name":"Hibernate In Action","author":"Gavin King","page":260,"price":50.78}
		],
		"otherList":[],
		"bookCountList":[
		  {"categoryId":1,"bookCount":11,"categoryName":"Java"},
		  {"categoryId":2,"bookCount":22,"categoryName":"PHP"},
		  {"categoryId":3,"bookCount":33,"categoryName":"Python"}
		]
	}
     }
-------------------------------------------------------------------------
     *
     * 注意1: "header":{}, header 里面通常为: 空的 json 对象, 不确定 json 对象 的格式
     * 注意2: "otherList":[], otherList 里面通常为: 空的 json 数组, 不确定 json 对象 的格式
     */
    public static void main(String[] args) {
        String str ="hello" ;
        String json = java2json(str) ;

        System.out.println( json );
        String s = json2java(json,String.class);
        System.out.println(s);
    }

}
