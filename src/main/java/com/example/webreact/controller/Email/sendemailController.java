

package com.example.webreact.controller.Email;

import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.JavaSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Email")
public class sendemailController {
    @PostMapping("/sendEmail")
    public Response email() throws Exception {
        JavaSocket.sendMail(new EmailModel("设计得十分接近","mmmmmsdf史蒂夫纳什电脑","luckyoness@163.com"),1);
        return new Response(true,"成功！",200);
    }

}
