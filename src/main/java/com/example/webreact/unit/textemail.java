package com.example.webreact.unit;

import com.example.webreact.entity.Email.EmailModel;
import jakarta.mail.internet.MimeMessage;

public class textemail {
    //纯文本邮件
    public static void messageWord(MimeMessage message, EmailModel emailModel) throws Exception {
        //邮件的标题
        message.setSubject(emailModel.getTitle());
        //处理正文内容不换行
        String sendHtml="";
        if (emailModel.getMessage() != null && !"".equals(emailModel.getMessage().trim()))
            sendHtml= emailModel.getMessage().replaceAll("\n", "<br>").replaceAll("\n", "<BR>");
        else
            sendHtml = "";
        //邮件的文本内容
        message.setContent(sendHtml, "text/html;charset=UTF-8");
    }
}
