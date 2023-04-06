package com.example.webreact.entity;

public class uploadimage {
    private String name;
    private String url;

    private String logs;
    private  String country;

    private String createtime;
    public uploadimage(String name, String url, String logs, String country, String createtime) {
        this.name = name;
        this.url = url;
        this.logs = logs;
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

    public String getLogs() {
        return logs;
    }

    public uploadimage setLogs(String logs) {
        this.logs = logs;
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
