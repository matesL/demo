package com.example.webreact.server.Email;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.Email.Useremail;
import jakarta.servlet.http.HttpServletRequest;

public interface sendEmailServer {
    Response send_list(Useremail useremail);


}
