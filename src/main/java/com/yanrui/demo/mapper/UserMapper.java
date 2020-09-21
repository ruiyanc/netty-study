package com.yanrui.demo.mapper;

import com.yanrui.demo.pojo.User;
import com.yanrui.demo.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 许睿
 */
@Mapper
public interface UserMapper extends MyMapper<User> {
}