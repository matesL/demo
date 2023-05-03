/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.server.user;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.basecat.LoginDto;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.entity.Email.Useremail;


public interface IUserService {
    LoginDto login(UserInfo user);
    LoginDto singup(UserInfo user);
    /**
     * 新增邮箱信息
     */
    Response addemail (Useremail useremail);

    /**
     * 查询信息
     */
    Response select (Useremail useremail);

}
