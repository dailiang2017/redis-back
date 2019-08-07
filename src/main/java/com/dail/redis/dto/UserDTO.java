package com.dail.redis.dto;

import com.dail.redis.enums.IsDeletedEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: dailiang
 * @Date: 2018/12/29 17:33
 * @Description:
 */
@Data
public class UserDTO extends BaseModel {

    private String username;

    private String realname;

    private String password;

    public void preInsert(Long userId) {
        Date now = new Date();
        this.setCreator(userId);
        this.setModifier(userId);
        this.setCreateTime(now);
        this.setModifyTime(now);
    }

    public void preUpdate(Long userId) {
        Date now = new Date();
        this.setModifier(userId);
        this.setModifyTime(now);
    }

    public void preDelete(Long userId, Long id) {
        Date now = new Date();
        this.setId(id);
        this.setModifier(userId);
        this.setModifyTime(now);
        this.setIsDeleted(IsDeletedEnum.Y.getCode());
    }
}
