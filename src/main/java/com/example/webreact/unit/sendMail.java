package com.example.webreact.unit;

import com.example.webreact.entity.Email.EmailModel;
import com.example.webreact.entity.Email.Useremail;
import com.example.webreact.entity.Email.pop3_stmp;
import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;

import static com.example.webreact.unit.textemail.messageWord;
import static com.example.webreact.unit.filesemail.messagePic;

import java.io.File;
import java.util.Properties;

public class sendMail {

    /**
     * 邮箱服务协议
     * @return
     * @throws Exception
     */
    public static Properties getProperties(pop3_stmp smtpserver) throws Exception{

        //host服务端口
        Properties prop = new Properties();
        prop.setProperty("mail.host", smtpserver.getHost() );
//        设置QQ邮件服务器
        //邮箱协议
        prop.setProperty("mail.transport.protocol", smtpserver.getType_text()); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        //SSL_IMPT
        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        return prop;
    }

    /**
     * @param file
     * @param type 1:纯文本邮件、 2：文本+图片邮件
     * @return
     */
    public static boolean sendMail(EmailModel emailModel, pop3_stmp smtpserver, Useremail useremail,MultipartFile[] file,MultipartFile[] image, int type) throws Exception{

        //使用JavaMail发送邮件的5个步骤
        //1、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(getProperties(smtpserver), new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
//                return new PasswordAuthentication(emailId_send, emailKey);
                return new PasswordAuthentication(emailModel.getSend_email(),useremail.getKey_pop() );
            }
        });

        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和授权码连上邮件服务器
//        emailId_send=model.getSend_email();
//        emailKey=model.getKey_pop();
        ts.connect(smtpserver.getHost(), emailModel.getSend_email(), useremail.getKey_pop());

        //4、创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
//        设置昵称
        String nick= jakarta.mail.internet.MimeUtility.encodeText(useremail.getNick());
        //指明邮件的发件人
        message.setFrom(new InternetAddress(nick+"<"+emailModel.getSend_email()+">"));

        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailModel.getTo_email()));
        //邮件的封装 【纯文本邮件、图片邮件等】
//        messageWord(message,emailModel);
        try{
            if(type == 1) messageWord(message,emailModel) ;
            else if(type ==2) messagePic(message,emailModel,file,image);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            // re=ts
            ts.close();

        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

}
