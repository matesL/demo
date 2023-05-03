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

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class filesemail {
    //图片邮件
    public static void messagePic(MimeMessage message, EmailModel emailModel, MultipartFile[] files, MultipartFile[] image) throws Exception {
        //邮件标题
        message.setSubject(emailModel.getTitle(), "UTF-8");


        // 准备图片数据
        MimeBodyPart imageboby = new MimeBodyPart();



        // 准备正文数据
        MimeBodyPart text1 = new MimeBodyPart();

//        body3.setDataHandler(new DataHandler(new FileDataSource(file)));
//        body3.setFileName(file.getName()); //附件设置名字
        MimeMultipart mm = new MimeMultipart();

        if (!image[0].isEmpty()) {
            DataHandler dh = new DataHandler(image[0].getBytes(), "application/octet-stream");
//                    new DataHandler(new FileDataSource(image));
            imageboby.setDataHandler(dh);
            String filename = String.valueOf(Objects.requireNonNull(image[0].getOriginalFilename()).lastIndexOf("."));
            imageboby.setContentID(filename);

            text1.setContent(emailModel.getMessage() + "<img src='cid:" + filename + "'>", "text/html;charset=UTF-8");

            mm.addBodyPart(text1);
            mm.addBodyPart(imageboby);
            mm.setSubType("related");

        } else {
            text1.setContent(emailModel.getMessage(), "text/html;charset=UTF-8");
            mm.addBodyPart(text1);
//            mm.addBodyPart(imageboby);
            mm.setSubType("related");
        }


        //设置正文

        //附件
        MimeBodyPart body3 = new MimeBodyPart();
        /************3.文件流  添加多个附件*************/
        String attachName = "";
        for (MultipartFile file : files) {
            DataSource dataSource = new ByteArrayDataSource(file.getBytes(), "application/octet-stream");
            body3.setDataHandler(new DataHandler(dataSource));
            // 处理邮件中附件文件名的中文问题
            attachName = MimeUtility.encodeText(Objects.requireNonNull(file.getOriginalFilename()));
            // 设定附件文件名
        }
        MimeBodyPart contentText = new MimeBodyPart();
        contentText.setContent(mm);
        //拼接附件
        MimeMultipart allFile = new MimeMultipart();

        allFile.addBodyPart(contentText); //正文
        body3.setFileName(attachName);
        // 添加附件
        allFile.addBodyPart(body3);
        allFile.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(allFile, "text/html;charset=UTF-8");
        message.saveChanges();
    }
}
