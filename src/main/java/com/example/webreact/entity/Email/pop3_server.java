package com.example.webreact.entity.Email;

public class pop3_server {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPop3_typeid() {
        return pop3_typeid;
    }

    public void setPop3_typeid(int pop3_typeid) {
        this.pop3_typeid = pop3_typeid;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getProperty() {
        return Property;
    }

    public void setProperty(String property) {
        Property = property;
    }
    public int id;
    public int user_id;
    public int pop3_typeid;
    public String smtp;
    public  String Property;
}
