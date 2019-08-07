package com.dail.redis.results;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: dailiang
 * @Date: 2018/12/26 17:52
 * @Description:
 */
@Data
public class CacheResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success = true;

    private T data;
}
