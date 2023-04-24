package com.example.webreact.mapper.post;

import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.Email.Useremail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    /**
     *
     * 发送邮件将，发送内容添加到数据，记录发送信息
     * id
     * send_email
     * to_email
     * key
     * username
     */
    int send_list(EmailModel model);

    /**
     * 查询历史记录
     */
    UserInfo getlist_seng(int id);
    /**
     *
     * 如果用户执行历史记录查询
     */
   List<EmailModel>  data_list(int id);
//    List<Map<String,Object>>   data_list(List<Integer>  id);
}
