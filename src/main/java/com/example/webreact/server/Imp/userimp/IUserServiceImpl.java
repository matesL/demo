/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.server.Imp.userimp;

import com.example.webreact.entity.Email.pop3_server;
import com.example.webreact.entity.JWT.TokenUtil;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.entity.Email.Useremail;
import com.example.webreact.mapper.post.sendemailMapper;
import com.example.webreact.server.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.webreact.entity.basecat.LoginDto;
import com.example.webreact.mapper.post.userMapper;

import java.util.ArrayList;
import java.util.List;


@Component
@Service
public class IUserServiceImpl implements IUserService {

        @Autowired
        private userMapper userMapper;

        @Autowired
        private sendemailMapper  sendemailMappers;
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
     * 添加绑定邮箱信息
     * @param useremail
     */
    @Override
    public Response addemail(Useremail useremail) {
        List<Useremail> data =new ArrayList<>();
        pop3_server pop3Server= new pop3_server();
        data.add(useremail);
        System.out.println(useremail.getUser_email());
        List<Useremail> email_count= sendemailMappers.emailcount(useremail.getUser_id(),useremail.getUser_email());
        if (email_count.size()>=1) {
            return new Response(false,"失败！ 该邮箱已存在！",402,data);
        }
        try{
            int  result=  sendemailMappers.addemail(useremail);
               if (result==1){
                   //邮箱添加成功，记录服务类型
                   pop3Server.setPop3_typeid(useremail.getPop_type());
                   pop3Server.setUser_id(useremail.getUser_id());
                   pop3Server.setSmtp(String.valueOf(useremail.getType_id()));
                    pop3Server.setProperty(useremail.getUser_email());
                   sendemailMappers.addPOPserver(pop3Server);
                   return new Response(true,"添加成功！",200,data);
               }
               else {
                   return new Response(false,"失败！ 请填写完整",400,data);
               }
                }
        catch (Exception e){
              return new Response(false,e.getMessage(),400,data);
        }
    }

    /**
     * 查询 用户绑定邮箱信息
     *
     * @param useremail
     */
    @Override
    public Response select(Useremail useremail) {
        return null;
    }

}
