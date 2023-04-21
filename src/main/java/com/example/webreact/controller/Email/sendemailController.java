

package com.example.webreact.controller.Email;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.Email.Useremail;
import com.example.webreact.mail.JavaSocket;
import com.example.webreact.server.Imp.emailImp.sendemailSericeImp;
import com.example.webreact.server.Imp.userimp.IUserServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/Email")
public class sendemailController {
    @Autowired
    private IUserServiceImpl userService;
    @Autowired
    private sendemailSericeImp sendemailSericeImp;
    /**
     * 查询发送信息   一个用户绑定多个账号 按照
     * @return
     * @throws Exception
     */
    @PostMapping("/sendEmail")
    public Response email(Useremail useremail) throws Exception {
//
//        JavaSocket.sendMail(new EmailModel("设计得十分接近","mmmmmsdf史蒂夫纳什电脑","luckyoness@163.com"),1);
        return sendemailSericeImp.send_list(useremail);
    }

    /**
     * 插入邮箱发送信息
     * @param useremail
     * @return
     * @throws Exception
     */
    @PostMapping("/install")
    public Response install(Useremail useremail) {
        System.out.println(useremail.key_pop+""+"sdsfs");
        System.out.println(useremail.getUser_id()+""+"2222");
        return userService.inster(useremail);
    }
}
