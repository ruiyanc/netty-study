package com.yanrui.demo.service.impl;

import com.yanrui.demo.mapper.UserMapper;
import com.yanrui.demo.pojo.User;
import com.yanrui.demo.service.UserService;
import com.yanrui.demo.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 许睿
 * @version 1.0
 * @description
 * @date 2020/9/16 0:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user) != null;
    }


//    查询事务级别
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String username, String password) {
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);

        return userMapper.selectOneByExample(userExample);
    }

    @Override
    public User saveUser(User user) {
        String sid = KeyUtil.genUniqueKey();
//        TODO 为每个用户生成一个唯一的二维码
        user.setQrcode("");

        user.setId(sid);
        userMapper.insert(user);
        return user;
    }

}
