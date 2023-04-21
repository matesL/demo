package com.example.webreact.mapper.post;

import com.example.webreact.entity.basecat.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper  {

//    登录查询
        UserInfo login(UserInfo userInfo);

        /**
         * 注册
         * @param userInfo
         * @return
         */
        int singup(UserInfo userInfo);
}
