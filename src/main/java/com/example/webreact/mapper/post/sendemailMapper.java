package com.example.webreact.mapper.post;

import com.example.webreact.entity.Email.pop3_server;
import com.example.webreact.entity.Email.pop3_stmp;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.entity.Email.EmailModel;
import com.example.webreact.entity.Email.Useremail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface sendemailMapper {
    /**
     *用户添加有绑定邮箱
     */
    int addemail(Useremail useremail);


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

    /**
     * 用户添加成功绑定邮箱后 更新pop3服务
     */
    int addPOPserver(pop3_server pop3Server);

    /**
     * 查询端口类型
     * @param type_id 1 QQ邮箱 2 网易邮箱  ｜｜ type 1、smtp 2、pop
     * @return
     */
    pop3_stmp pop_data(int type_id, int pop_id);

    /**
     * 查询key——pop
     */
    Useremail pop_keytostring(int user_id,String user_email);

    /**
     * 查询添加邮箱是否存在
     * @param user_email
     * @return
     * @Date new
     */
    List<Useremail> emailcount(int user_id,String user_email);
}
