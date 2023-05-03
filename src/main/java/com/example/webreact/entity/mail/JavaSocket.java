
package com.example.webreact.entity.mail;

import com.example.webreact.entity.Email.Useremail;
import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import  com.example.webreact.entity.Email.EmailModel;
import java.util.Properties;

public class JavaSocket {

    private static final String emailKey = "HEINYMWEEAJYPXMB"; //发件人邮箱的授权码
    private static final String emailId_send = "2064058933@qq.com";
    private static final String emailId_receicve = "";

    public static Properties getProperties() throws Exception{

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
//        设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        return prop;
    }

    /**
     * @param type 1:纯文本邮件、 2：文本+图片邮件
     * @return
     */
    public static boolean sendMail(EmailModel emailModel, int type) throws Exception{

        //使用JavaMail发送邮件的5个步骤
        //1、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
//                return new PasswordAuthentication(emailId_send, emailKey);
                return new PasswordAuthentication(emailModel.getSend_email(), emailKey);
            }
        });

        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和授权码连上邮件服务器
//        使用数据库获取key
        Useremail model=new Useremail();

//        emailId_send=model.getSend_email();
//        emailKey=model.getKey_pop();
        ts.connect("smtp.163.com", emailModel.getSend_email(), emailKey);
        //4、创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(emailModel.getSend_email()));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailModel.getTo_email()));
        //邮件的封装 【纯文本邮件、图片邮件等】
        messageWord(message,emailModel);
       try{ if(type == 1) messageWord(message,emailModel) ;
        else if(type ==2) messagePic(message,emailModel);
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

    //纯文本邮件
    public static void messageWord(MimeMessage message,EmailModel emailModel) throws Exception {
        //邮件的标题
        message.setSubject(emailModel.getTitle());
        //邮件的文本内容
        message.setContent(emailModel.getMessage(), "text/html;charset=UTF-8");
    }
    //图片邮件
    public static void messagePic(MimeMessage message,EmailModel emailModel) throws Exception {
        //邮件标题
        message.setSubject(emailModel.getTitle());
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("uploads/59a2935b4e424257b764d4276a578949.png"));
        image.setDataHandler(dh);
        image.setContentID("pic1.png");

        // 准备正文数据
        MimeBodyPart text1 = new MimeBodyPart();
        text1.setContent("这是一封邮件正文带图片<img src='cid:pic1.png'>的邮件", "text/html;charset=UTF-8");

        //附件
        MimeBodyPart body3 = new MimeBodyPart();
        body3.setDataHandler(new DataHandler(new FileDataSource("")));
        body3.setFileName("SSSS"); //附件设置名字

        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text1);
        mm.addBodyPart(image);
        mm.setSubType("related");

        MimeBodyPart contentText =  new MimeBodyPart();
        contentText.setContent(mm);

        //拼接附件
        MimeMultipart allFile =new MimeMultipart();
        allFile.addBodyPart(body3);  //附件
        allFile.addBodyPart(contentText); //正文
        allFile.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(allFile);
        message.saveChanges();
    }



    public static void main(String[] args) {
        try {
//            JavaSocket.sendMail("title", "message","luckyoness@163.com",1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
