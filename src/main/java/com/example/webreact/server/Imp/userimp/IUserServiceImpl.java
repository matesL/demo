/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.server.Imp.userimp;

import com.example.webreact.entity.JWT.TokenUtil;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.mail.Email.Useremail;
import com.example.webreact.mapper.post.sendemailMapper;
import com.example.webreact.server.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.webreact.entity.basecat.LoginDto;
import com.example.webreact.mapper.post.userMapper;
@Component
@Service
public class IUserServiceImpl implements IUserService {

        @Autowired
        private userMapper userMapper;

        @Autowired
        private sendemailMapper  sendemailMapper;
        @Override
        public LoginDto login(UserInfo user) {
            System.out.println(user);
            LoginDto loginDto = new LoginDto();
            UserInfo login = userMapper.login(user);
            if (login == null) {
                loginDto.setCode(400);
                loginDto.setMsg("账号或密码错误！");
                return loginDto;
            }
            String token = TokenUtil.sign(login);

            loginDto.setCode(200);
            loginDto.setMsg("登录成功!");
            loginDto.setUserInfo(login);
            loginDto.setToken(token);
            return loginDto;
        }

    /**
     * @param user
     * @return
     */
    @Override
    public LoginDto singup(UserInfo user) {
        LoginDto loginDto = new LoginDto();
        int login = userMapper.singup(user);
        UserInfo logins = new UserInfo();
        logins.setId(1001);
        logins.setPassword(user.getPassword());
        logins.setEmail(user.getEmail());
        logins.setUsername(user.getUsername());
        if (login <= 0) {
            loginDto.setCode(400);
            loginDto.setMsg("请刷新后重试！");
            loginDto.setUserInfo(logins);
            return loginDto;
        }

        loginDto.setCode(200);
        loginDto.setMsg("注册成功!");
        loginDto.setUserInfo(logins);
        return loginDto;
    }

    /**
     * 新增邮箱信息
     * @param useremail
     */
    @Override
    public Response inster(Useremail useremail) {

       if (useremail.send_email==null){
           System.out.println(useremail.user_id);
           return new Response(false,"失败！ 请填写完整",400);
       }
       else {
          int  useremail1=  sendemailMapper.insert(useremail);
           return new Response(true,"添加成功！",200);
       }
    }

    /**
     * 查询信息
     *
     * @param useremail
     */
    @Override
    public Response select(Useremail useremail) {
        return null;
    }

}
