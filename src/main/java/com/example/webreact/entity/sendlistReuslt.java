package com.example.webreact.entity;

import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.entity.Email.EmailModel;
import java.util.List;
public class sendlistReuslt {
    String msg;
    int code;
    Boolean isSuc = true;
    public List<EmailModel> getList() {
        return list;
    }

    public void setList(List<EmailModel> list) {
        this.list = list;
    }

    List<EmailModel> list;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getSuc() {
        return isSuc;
    }

    public void setSuc(Boolean suc) {
        isSuc = suc;
    }



    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    UserInfo userInfo;
}
