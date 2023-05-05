package com.example.webreact.controller.Email;


import com.alibaba.fastjson2.JSONObject;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.sendlistReuslt;
import com.example.webreact.entity.Email.EmailModel;
import com.example.webreact.entity.Email.Useremail;
import com.example.webreact.server.Imp.emailImp.sendemailSericeImp;
import com.example.webreact.server.Imp.uploadServerImp;
import com.example.webreact.server.Imp.userimp.IUserServiceImpl;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.List;


@RestController
@RequestMapping(value = "/Email")
public class sendemailController {
    @Autowired
    private IUserServiceImpl userService;
    @Autowired
    private sendemailSericeImp sendemailSericeImp;
    @Autowired
    private com.example.webreact.server.Imp.uploadServerImp uploadServerImp;
    /**
     * 发送邮件请求处理
     *
     * @return
     * @throws
     */
    @PostMapping("/sendEmail")
    public Response email(EmailModel useremail,@RequestParam(value = "file",required = false) MultipartFile[] file,@RequestParam(value = "image",required = false) MultipartFile[] image, HttpServletRequest req) throws Exception {
        if (file!=null || image!=null ) {

            System.out.println(1 + ": type");
            List url =uploadServerImp.upload_pic(image,req,useremail.getUser_id());
            return sendemailSericeImp.file_sendEmail(useremail, file, image, String.valueOf(url));
        }
        else {
            System.out.println(2 + ": type");
            List url =uploadServerImp.upload_pic(image,req,useremail.getUser_id());
//            Response response = sendemailSericeImp.file_sendEmail(useremail, file, image,url);
            return sendemailSericeImp.send_list(useremail, String.valueOf(url));
        }

    }

    /**
     * 用户添加绑定邮箱服务
     * send_mail  pop_key
     *
     * @param useremail
     * @return Response
     * @throws Exception
     */
    @PostMapping("/addemail")
    public Response install(Useremail useremail) {

        return userService.addemail(useremail);
    }

    /**
     * 查询历史发送记录
     * 通过id 查询
     */
    @GetMapping("/getsendlist")
    public sendlistReuslt getlistmail_send(int id) {
        System.out.println(id);
        return sendemailSericeImp.getlist_send(id);
    }
}
