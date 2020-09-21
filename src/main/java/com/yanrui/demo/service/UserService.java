package com.yanrui.demo.service;

import com.yanrui.demo.pojo.User;

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
}
