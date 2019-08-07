package com.dail.redis.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {

    private Long id;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人
     */
    private Long modifier;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    /**
     * N正常,Y删除
     */
    private String isDeleted;

    /**
     * 每页条数
     */
    private int limit        = 20;

    /**
     * 起始条数
     */
    private int start        = 0;
    /**
     * 是否需要 分页
     */
    boolean     isNeedPaging = true;
}
