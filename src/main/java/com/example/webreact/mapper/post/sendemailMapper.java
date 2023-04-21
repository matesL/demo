package com.example.webreact.mapper.post;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.Email.Useremail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface sendemailMapper {
    /**
     *新增邮箱
     */
    int insert(Useremail useremail);
    /**
     * 查询信息
     * model send_email 发送人  to_email 接收人   key  pop3服务地址
     */
    Useremail select(Useremail useremail);
}
