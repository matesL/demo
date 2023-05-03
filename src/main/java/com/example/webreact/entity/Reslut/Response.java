package com.example.webreact.entity.Reslut;

import java.util.ArrayList;
import java.util.List;

public class Response {

    String msg;
    int code;
    Boolean isSuc = true;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    List<?> data=new ArrayList<>();
    public Response(Boolean isSuc, String msg, int code,List<?> data) {
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
        if (data == null) {
            this.data=null;
        }
        this.data=data;
    }



    public Boolean getIsSuc() {
        return isSuc;
    }
    public void setIsSuc(Boolean isSuc) {
        this.isSuc = isSuc;
    }
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
}
