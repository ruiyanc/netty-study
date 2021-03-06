package com.yanrui.demo.service.impl;

import com.yanrui.demo.enums.SearchFriendStatusEnum;
import com.yanrui.demo.mapper.FriendMapper;
import com.yanrui.demo.mapper.UserMapper;
import com.yanrui.demo.mapper.UserRequestMapper;
import com.yanrui.demo.pojo.Friend;
import com.yanrui.demo.pojo.User;
import com.yanrui.demo.pojo.UserRequest;
import com.yanrui.demo.service.UserService;
import com.yanrui.demo.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

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

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserRequestMapper userRequestMapper;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer preconditionSearchFriends(String userId, String friendUsername) {
        User user = queryUserInfoByUsername(friendUsername);
        //搜索的用户不存在
        if (user == null) {
            return SearchFriendStatusEnum.USER_NO_EXIST.status;
        }else {
            //搜索的账号是自己
            if (user.getId().equals(userId)) {
                return SearchFriendStatusEnum.NOT_YOURSELF.status;
            }
            //搜索的朋友已经是我的好友
            Example efd = new Example(Friend.class);
            Example.Criteria cfd = efd.createCriteria();
            cfd.andEqualTo("userId", userId);
            cfd.andEqualTo("friendUserId", user.getId());
            Friend friend = friendMapper.selectOneByExample(efd);
            if (friend != null) {
                return SearchFriendStatusEnum.ALREADY_FRIENDS.status;
            }
        }
        return SearchFriendStatusEnum.SUCCESS.status;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserInfoByUsername(String username) {
        Example eu = new Example(User.class);
        Example.Criteria cu = eu.createCriteria();
        cu.andEqualTo("username", username);
        return userMapper.selectOneByExample(eu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return queryUserById(user.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void sendFriendRequest(String userId, String friendUsername) {
//        根据用户名把朋友信息查询出来
        User friend = queryUserInfoByUsername(friendUsername);

//        查询发送好友请求记录表
        Example ur = new Example(UserRequest.class);
        Example.Criteria urc = ur.createCriteria();
        urc.andEqualTo("sendUserId", userId);
        urc.andEqualTo("acceptUserId", friend.getId());
        UserRequest userRequest = userRequestMapper.selectOneByExample(ur);
        if (userRequest == null) {
//            如果不是我的好友，并没有好友记录添加则新增好友请求记录
            String requestId = KeyUtil.genUniqueKey();

            UserRequest request = new UserRequest();
            request.setId(requestId);
            request.setSendUserId(userId);
            request.setAcceptUserId(friend.getId());
            request.setRequestDateTime(new Date());
            userRequestMapper.insert(request);

        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


}
