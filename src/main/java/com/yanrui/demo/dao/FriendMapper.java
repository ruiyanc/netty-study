package com.yanrui.demo.dao;

import com.yanrui.demo.pojo.Friend;

public interface FriendMapper {
    int deleteByPrimaryKey(String id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}