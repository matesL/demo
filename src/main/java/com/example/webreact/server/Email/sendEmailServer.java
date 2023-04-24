package com.example.webreact.server.Email;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.sendlistReuslt;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.Email.Useremail;

import java.util.List;

public interface sendEmailServer {
    Response send_list(Useremail useremail);

    sendlistReuslt getlist_send(int id );

    List<EmailModel> data_list(int user_id);

}
