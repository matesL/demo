package com.example.webreact.mail.Email;

public class Useremail {


    public String send_email;

    public String getSend_email() {
        return send_email;
    }

    public void setSend_email(String send_email) {
        this.send_email = send_email;
    }

    public String getTo_email() {
        return to_email;
    }

    public void setTo_email(String to_email) {
        this.to_email = to_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getKey_pop() {
        return key_pop;
    }

    public void setKey_pop(String key_pop) {
        this.key_pop = key_pop;
    }

    public String to_email;

//    @Override
//    public String toString() {
//        return "Useremail{" +
//                "send_email='" + send_email + '\'' +
//                ", to_email='" + to_email + '\'' +
//                ", user_id='" + user_id + '\'' +
//                ", key_pop='" + key_pop + '\'' +
//                '}';
//    }

    public String user_id;
    public String key_pop;

}
