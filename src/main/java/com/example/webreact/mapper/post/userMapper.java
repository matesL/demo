package com.example.webreact.mapper.post;

import com.example.webreact.entity.basecat.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface userMapper  {

//    登录查询
    UserInfo select(UserInfo userInfo);
}
