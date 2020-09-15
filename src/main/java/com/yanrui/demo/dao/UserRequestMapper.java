package com.yanrui.demo.dao;

import com.yanrui.demo.pojo.UserRequest;

public interface UserRequestMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRequest record);

    int insertSelective(UserRequest record);

    UserRequest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRequest record);

    int updateByPrimaryKey(UserRequest record);
}