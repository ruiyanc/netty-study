package com.yanrui.demo.service;

import com.yanrui.demo.pojo.User;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 许睿
 * @version 1.0
 * @description
 * @date 2020/9/16 0:23
 */
public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);


    /**
     * 查询用户是否存在
     * @param username
     * @param password
     * @return
     */
    public User queryUserForLogin(String username, String password);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public User saveUser(User user);

    /**
     * 搜索朋友的前置条件
     * @param myUserId
     * @param friendUsername
     * @return
     */
    public Integer preconditionSearchFriends(String myUserId, String friendUsername);

    /**
     * 根据用户名获取User对象
     * @param username
     * @return
     */
    public User queryUserInfoByUsername(String username);

    /**
     * 修改昵称
     * @param user
     * @return
     */
    User updateUserInfo(User user);

    /**
     * 添加好友请求记录
     * @param userId
     * @param friendUsername
     */
    void sendFriendRequest(String userId, String friendUsername);
}
