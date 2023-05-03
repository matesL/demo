package com.example.webreact.unit;

import com.example.webreact.entity.Email.EmailModel;
import com.example.webreact.entity.Email.Useremail;
import com.example.webreact.entity.Email.pop3_stmp;
import io.micrometer.common.util.StringUtils;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Properties;



public class javaMail {

    public static String host="smtp";
    public static String port="465";
    public static String username="admin@163.com";
    public static String password="";

    /**
     * 发送带附件的邮件
     * @param receiver 发件人
     * @param title 主题
     * @param content 邮件内容
     * @param fileName 附件名
     * @param inputstream 附件流
     * @throws Exception
     */
    public void sendEmail(EmailModel emailModel, pop3_stmp smtpserver, Useremail useremail, MultipartFile[] file) throws Exception {

        // 加载配置文件
        Properties props = new Properties();
        try {
            props.put("mail.smtp.ssl.trust", smtpserver.getHost());
            props.setProperty("mail.transport.protocol", smtpserver.getType_text());   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", smtpserver.getHost());   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
            // port SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
            //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
            //                  QQ邮箱的SMTP(SLL)端口为465或587)
            props.setProperty("mail.smtp.port", port);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", port);
            // log.info(props.get("mail.smtp.ssl.trust").toString());
        } catch (Exception e) {
            e.printStackTrace();
            //log.debug(e.getMessage());
        }
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        try {
            // 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            MimeMessage message = new MimeMessage(session);
            // 创建 MimeMessageHelper 对象
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "utf-8");
            // 设置发件人地址
            mimeMessageHelper.setFrom(new InternetAddress(emailModel.getSend_email()));
            InternetAddress[] tos=InternetAddress.parse(emailModel.getTo_email());
            // 设置收件人地址
            mimeMessageHelper.setTo(tos);
            // 设置主题
            mimeMessageHelper.setSubject(emailModel.getTitle());
            // 设置 消息内容
            //MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
//            Multipart mainPart = new MimeMultipart();  //HTML 格式
            MimeMultipart contentMultipart=new MimeMultipart("related");
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(emailModel.getMessage(), "text/html; charset=utf-8");
            contentMultipart.addBodyPart(html);
            //附件部分
            //可以设置多个附件
            if(StringUtils.isNotBlank("dsd.doc"))
            {
                ByteArrayInputStream stream=new ByteArrayInputStream(file[0].getBytes());
                MimeBodyPart excelBodyPart=new MimeBodyPart();
                DataSource dataSource=new ByteArrayDataSource(stream, "application/excel");
                DataHandler dataHandler=new DataHandler(dataSource);
                excelBodyPart.setDataHandler(dataHandler);
                excelBodyPart.setFileName(file[0].getName());
                contentMultipart.addBodyPart(excelBodyPart);
            }
            // 将MiniMultipart对象设置为邮件内容
            message.setContent(contentMultipart);
            // 发送消息
            transport.connect(emailModel.getSend_email(), useremail.getKey_pop());
            //  发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 关闭连接
            transport.close();
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
