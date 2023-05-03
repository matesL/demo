package com.example.webreact.server.Email;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.sendlistReuslt;
import com.example.webreact.entity.Email.EmailModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface sendEmailServer {
    Response send_list(EmailModel useremail,String url);

    Response file_sendEmail(EmailModel email, MultipartFile[] file,MultipartFile[] image,String url);

    sendlistReuslt getlist_send(int id );

    List<EmailModel> data_list(int user_id);

}
