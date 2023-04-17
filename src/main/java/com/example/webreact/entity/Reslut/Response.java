package com.example.webreact.entity.Reslut;

public class Response {

    String msg;
    int code;
    Boolean isSuc = true;

    public Response(Boolean isSuc, String msg, int code) {
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
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
