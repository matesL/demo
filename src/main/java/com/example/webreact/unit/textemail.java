package com.example.webreact.unit;

import com.example.webreact.entity.Email.EmailModel;
import jakarta.mail.internet.MimeMessage;

public class textemail {
    //纯文本邮件
    public static void messageWord(MimeMessage message, EmailModel emailModel) throws Exception {
        //邮件的标题
        message.setSubject(emailModel.getTitle());

        //邮件的文本内容
        message.setContent(emailModel.getMessage(), "text/html;charset=UTF-8");
    }
}
