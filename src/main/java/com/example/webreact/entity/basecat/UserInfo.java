package com.example.webreact.entity.basecat;


import lombok.Data;

/**
 * 用户表
 */
@Data
public class UserInfo {

  private long id;
  private String username;
  private String password;
  private String email;

  public long getId() {
    return id;
  }

  public UserInfo setId(long id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserInfo setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserInfo setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserInfo setEmail(String email) {
    this.email = email;
    return this;
  }



}
