package com.ue.termi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ue.termi.entity.User;

import java.util.List;

/**
 * @program: iserver
 * @description: description
 * @author: youyi
 * @create: 2019-12-29 18:16
 **/
public interface UserService extends IService<User> {
    List<User> getList();
    Integer addUser(User user);

    List<User> txTest();


}
