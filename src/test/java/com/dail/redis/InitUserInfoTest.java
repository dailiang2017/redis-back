package com.dail.redis;

import com.dail.redis.dto.UserDTO;
import com.dail.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: dail
 * @CreateDate: 2019/8/8 9:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitUserInfoTest {

    @Autowired
    private UserService userService;

    @Test
    public void batchInsertUser() {
        List<UserDTO> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            UserDTO dto = new UserDTO();
            dto.setUsername("username" + i);
            dto.setRealname("realname" + i);
            dto.setCreator(-1L);
            dto.setModifier(-1L);
            list.add(dto);
        }
        userService.insertList(list);
    }
}
