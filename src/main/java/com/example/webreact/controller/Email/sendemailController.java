package com.example.webreact.controller.Email;


import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.sendlistReuslt;
import com.example.webreact.entity.Email.EmailModel;
import com.example.webreact.entity.Email.Useremail;
import com.example.webreact.server.Imp.emailImp.sendemailSericeImp;
import com.example.webreact.server.Imp.uploadServerImp;
import com.example.webreact.server.Imp.userimp.IUserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;


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
    public Response email(EmailModel useremail, MultipartFile[] file, MultipartFile[] image, int type, HttpServletRequest req) throws Exception {
        if (type == 1) {
            System.out.println(1 + ": type");
            String url =uploadServerImp.upload_pic(file,req,useremail.getUser_id());
            return sendemailSericeImp.send_list(useremail,url);
        } else {
            System.out.println(2 + ": type");
           String url =uploadServerImp.upload_pic(file,req,useremail.getUser_id());
            Response response = sendemailSericeImp.file_sendEmail(useremail, file, image,url);
            return response;
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
