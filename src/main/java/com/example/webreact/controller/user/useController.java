/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.controller.user;

import com.example.webreact.entity.basecat.LoginDto;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.server.userimp.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class useController {
/**
 * @description 登录接口，返回token
 * @param userInfo
 * @author lucky
 * @date 2023/4/14 16:58
 */
    @Autowired
    private IUserServiceImpl userService;
    @PostMapping("/login")
    public LoginDto login( UserInfo user){
        System.out.println( user.getPassword()+" /n"+user.getUsername());
        LoginDto login = userService.login(user);
        return login;
    }
    /**
     * 测试
     * @return
     */
    @RequestMapping("/test")
    public Object test(){
        return "访问成功!";
    }
}
