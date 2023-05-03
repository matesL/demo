package com.example.webreact.entity.Email;

public class pop3_stmp {
    public int id ;
    public String host;
    public  int pop_id;
    public String type_text;
    public  int type_id;

    public int getPop_id() {
        return pop_id;
    }

    public void setPop_id(int pop_id) {
        this.pop_id = pop_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }



    public String getType_text() {
        return type_text;
    }

    public void setType_text(String type_text) {
        this.type_text = type_text;
    }


}
