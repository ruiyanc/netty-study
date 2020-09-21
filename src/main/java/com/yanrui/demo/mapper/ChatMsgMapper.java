package com.yanrui.demo.mapper;

import com.yanrui.demo.pojo.ChatMsg;
import com.yanrui.demo.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMsgMapper extends MyMapper<ChatMsg> {

}