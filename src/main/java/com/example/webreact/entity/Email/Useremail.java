package com.example.webreact.entity.Email;

/**
 * 用户绑定邮箱服务协议
 */
public class Useremail {

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getPop_type() {
        return pop_type;
    }

    public void setPop_type(int pop_type) {
        this.pop_type = pop_type;
    }

    public String getKey_pop() {
        return key_pop;
    }

    public void setKey_pop(String key_pop) {
        this.key_pop = key_pop;
    }


    public int id;
    public int type_id;  //邮箱类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int user_id;     //用户ID
    public String key_pop;     //pop3授权协议
    public String user_email;  //已绑定邮箱
    public  int pop_type;     //邮箱发送类型 1 QQ邮箱  2 163邮箱 3 其他邮箱
    public String nick;     //用户设置昵称

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
