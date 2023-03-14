package com.ue.termi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ue.termi.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> list();
}