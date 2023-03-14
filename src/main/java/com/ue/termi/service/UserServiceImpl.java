package com.ue.termi.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ue.termi.entity.User;
import com.ue.termi.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: iserver
 * @description: description
 * @author: youyi
 * @create: 2019-12-29 18:16
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @DS("slave")
    public List<User> getList() {
        return baseMapper.list();
    }

    @Override
    @DS("master")
    public Integer addUser(User user) {
        return baseMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @DS("master")
    public List<User> txTest() {
        return baseMapper.list();
    }
}
