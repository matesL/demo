package com.example.webreact.entity;
/**
 * @description 图片上传
 * @param null
 * defaultParamDescription
 * @author lucky
 * @date 2023/4/17 14:39
 */
public class uploadimage {
    private String name;
    private String url;

    private int user_id;
    private  String country;

    private String createtime;
    public uploadimage(String name, String url, int user_id, String country, String createtime) {
        this.name = name;
        this.url = url;
        this.user_id = user_id;
        this.country = country;
        this.createtime = createtime;
    }



    public String getName() {
        return name;
    }

    public uploadimage setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public uploadimage setUrl(String url) {
        this.url = url;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public uploadimage setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public uploadimage setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public uploadimage setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }
}
