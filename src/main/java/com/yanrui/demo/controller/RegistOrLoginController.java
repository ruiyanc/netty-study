package com.yanrui.demo.controller;

import com.yanrui.demo.pojo.User;
import com.yanrui.demo.pojo.vo.UserVO;
import com.yanrui.demo.service.UserService;
import com.yanrui.demo.utils.JSONResult;
import com.yanrui.demo.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 许睿
 * @version 1.0
 * @description
 * @date 2020/9/16 0:17
 */
@RestController
@RequestMapping("u")
public class RegistOrLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/registOrLogin")
    public JSONResult registOrLogin(@RequestBody User user) {
//        判断用户名和密码不能为空
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JSONResult.errorMsg("用户名或密码不能为空！");
        }
//        判断用户名是否存在，存在则登录，不存在则注册
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        User userResult = null;
        if (usernameIsExist) {
//            登录
            userResult = userService.queryUserForLogin(user.getUsername(),
                    MD5Util.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return JSONResult.errorMsg("用户名或密码不正确！");
            }
        } else {
//            注册
            user.setNickname(user.getUsername());
            user.setFaceImage(null);
            user.setFaceImageBig(null);
            user.setPassword(MD5Util.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userResult, userVO);
        return JSONResult.ok(userVO);
    }
}
