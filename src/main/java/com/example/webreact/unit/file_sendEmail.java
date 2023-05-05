package com.example.webreact.unit;

import com.example.webreact.entity.Email.EmailModel;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.*;

public class file_sendEmail {
    public static void messagePics(MimeMessage message, EmailModel emailModel, MultipartFile[] files, MultipartFile[] images) throws Exception {
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件

        //邮件标题
        message.setSubject(emailModel.getTitle(), "UTF-8");

        //处理正文内容不换行
        // 准备正文数据
        MimeBodyPart text1 = new MimeBodyPart();

        MimeMultipart mm = new MimeMultipart();

        MimeMultipart allFile = new MimeMultipart();

        StringBuilder sendHtml = new StringBuilder();
        if (emailModel.getMessage() != null && !"".equals(emailModel.getMessage()))
            sendHtml = new StringBuilder(emailModel.getMessage().replaceAll("\n", "<br>").replaceAll("\n", "<BR>"));
        else
            sendHtml = new StringBuilder();

        // 准备图片数据
        MimeBodyPart image;
//        MimeBodyPart image2= new MimeBodyPart();

        if (images != null) {
            for (int i = 0; i < images.length; i++) {
                image=new MimeBodyPart();

                DataHandler dh = new DataHandler(images[i].getBytes(), "application/octet-stream");
                //图片字节流
                image.setDataHandler(dh);
                image.setContentID(images[i].getOriginalFilename().substring(0, images[i].getOriginalFilename().lastIndexOf(".")));//对应正文的标记位置
                sendHtml.append("<br /><img src='cid:").append(images[i].getOriginalFilename().substring(0, images[i].getOriginalFilename().lastIndexOf("."))).append("'/>");
                text1.setContent(sendHtml.toString(), "text/html;charset=UTF-8");
                System.out.println(sendHtml);
                mm.addBodyPart(image);
                mm.setSubType("related");
            }


//            for (MultipartFile img : images) {
//                //获取文件名
//                String name = img.getOriginalFilename();
//                MimeBodyPart image1 = new MimeBodyPart();
//               String  filename = String.valueOf(name.substring(0, name.lastIndexOf(".")));
//                DataHandler dh = new DataHandler(img.getBytes(), "application/octet-stream");
//                //图片字节流
//                image.setDataHandler(dh);
//                image.setContentID(filename);//对应正文的标记位置
//                sendHtml.append("<br /><img src='cid:").append(filename).append("'/>");
//                text1.setContent(sendHtml.toString(), "text/html;charset=UTF-8");
//                mm.addBodyPart(image);
//                mm.setSubType("related");
//            }


        }

        text1.setContent(sendHtml.toString(), "text/html;charset=UTF-8");
        System.out.println(sendHtml.toString());
        mm.addBodyPart(text1);

        //附件
        MimeBodyPart body3 = new MimeBodyPart();
        if (null != files && files.length != 0) {
            for (MultipartFile file : files) {
                //获取文件流
                DataSource dataSource = new ByteArrayDataSource(file.getBytes(), "application/octet-stream");
                body3.setDataHandler(new DataHandler(dataSource));
                // 处理邮件中附件文件名的中文问题
                String attachName = MimeUtility.encodeText(Objects.requireNonNull(file.getOriginalFilename()));
                body3.setFileName(attachName);//设置文件名
                System.out.println("不是空的");
                allFile.addBodyPart(body3);  //附件
            }
        }


        MimeBodyPart contentText = new MimeBodyPart();
        contentText.setContent(mm);
        //拼接附件
        allFile.addBodyPart(contentText); //正文
        allFile.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(allFile);
        message.saveChanges();
    }


}