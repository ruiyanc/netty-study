package com.yanrui.demo.mapper;

import com.yanrui.demo.pojo.Friend;
import com.yanrui.demo.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendMapper extends MyMapper<Friend> {

}