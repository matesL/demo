/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.entity.basecat;

public class LoginDto {
    String msg;
    int Code;

    public String getToken() {
        return Token;
    }

    public LoginDto setToken(String token) {
        Token = token;
        return this;
    }

    String Token;
    public String getMsg() {
        return msg;
    }

    public LoginDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return Code;
    }

    public LoginDto setCode(int code) {
        Code = code;
        return this;
    }

    public Boolean getSuc() {
        return isSuc;
    }

    public LoginDto setSuc(Boolean suc) {
        isSuc = suc;
        return this;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public LoginDto setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    Boolean isSuc = true;
    UserInfo userInfo;

}
