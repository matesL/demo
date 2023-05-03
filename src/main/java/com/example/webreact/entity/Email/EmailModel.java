/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.entity.Email;

import jakarta.activation.FileDataSource;

public class EmailModel {
    /**
     * 邮件发送历史记录
     */
    public String title;  //邮件标题
    public String message; //邮件内容
    public String to_email; //邮件收件人
    public String send_email; //邮件发送人
    public String sendtime; //邮件发送时间
    public int user_id;  //用户id
    public int  state; //状态

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo_email() {
        return to_email;
    }

    public void setTo_email(String to_email) {
        this.to_email = to_email;
    }

    public String getSend_email() {
        return send_email;
    }

    public void setSend_email(String send_email) {
        this.send_email = send_email;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getPop_id() {
        return pop_id;
    }

    public void setPop_id(int pop_id) {
        this.pop_id = pop_id;
    }

    public String url; //附件地址
    public int type_id; //协议类型 1、QQ邮箱 2、网易邮箱  3、其他
    public int pop_id; //1、POP  2、smtp

    FileDataSource fileDataSource;

    public FileDataSource getFileDataSource() {
        return fileDataSource;
    }

    public void setFileDataSource(FileDataSource fileDataSource) {
        this.fileDataSource = fileDataSource;
    }
}

