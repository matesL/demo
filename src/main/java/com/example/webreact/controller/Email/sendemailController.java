

package com.example.webreact.controller.Email;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.entity.sendlistReuslt;
import com.example.webreact.mail.Email.Useremail;
import com.example.webreact.server.Imp.emailImp.sendemailSericeImp;
import com.example.webreact.server.Imp.userimp.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
     * @throws email
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
     * @return Response
     * @throws Exception
     */
    @PostMapping("/install")
    public Response install(Useremail useremail) {

        return userService.inster(useremail);
    }
    /**
     * 查询历史发送记录
     * 通过id 查询
     */
    @PostMapping ("/getsendlist")
    public sendlistReuslt getlistmail_send(int id){
        System.out.println(id);
//        System.out.println(id+" wwqe");

        return sendemailSericeImp.getlist_send(id);
    }
}
