package com.example.webreact.controller.user;

import com.example.webreact.entity.basecat.LoginDto;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.server.Imp.userimp.IUserServiceImpl;
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
        return userService.login(user);
    }
    /**
     * @description 注册
     * @param userInfo
     * @author lucky
     * @date 2023/4/14 16:58
     */

    @PostMapping("/inst")
    public LoginDto inst( UserInfo user){
        return userService.singup(user);
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
