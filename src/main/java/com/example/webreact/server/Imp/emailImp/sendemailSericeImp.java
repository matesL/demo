package com.example.webreact.server.Imp.emailImp;
import com.example.webreact.server.Imp.uploadServerImp;

import com.example.webreact.entity.Email.Useremail;
import com.example.webreact.unit.sendMail;
import com.example.webreact.entity.Email.pop3_stmp;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.basecat.UserInfo;
import com.example.webreact.entity.sendlistReuslt;
import com.example.webreact.entity.Email.EmailModel;
import com.example.webreact.server.Email.sendEmailServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class sendemailSericeImp implements sendEmailServer {


    @Autowired
    private com.example.webreact.mapper.post.sendemailMapper sendemailMapper;

    uploadServerImp uploadServerImps=new uploadServerImp();

    /**
     * 一般不带有附件的邮件
     *
     * @param email
     * @return
     */
    @Override
    public Response send_list(EmailModel email,String url) {
        List<EmailModel> emailModels = new ArrayList<>();
        try {
            //调用邮件发送服务
            /**
             * 优化，判断用户邮箱发送协议
             * 发送邮件获取 用户ID user_id / pop_type 类型1、QQ邮箱 2、网易邮箱  3、其他 / type_id  1、POP  2、smtp
             */
            //1、查询用户要发送的的绑定的key 通过 user_id 、user_email
            Useremail useremail = sendemailMapper.pop_keytostring(email.getUser_id(), email.getSend_email());
            if (useremail == null) {
                return new Response(false, "授权码不存在，请添加后重试！！！", 404, emailModels);
            }
            //2、确定协议的host端口 和类型是smtp或pop
            pop3_stmp smtpserver = sendemailMapper.pop_data(email.type_id, email.pop_id);

            boolean t = sendMail.sendMails(email, smtpserver, useremail,null,null,1);
            /**
             * 存入数据
             */
            int state = 0;
            if (!t) {
                state = 2;
            } else {
                state = 1;
            }
            email.setState(state);
            email.setUrl(url);
            sendemailMapper.send_list(email);
            if (state == 2) {
                return new Response(false, "邮件发送失败，请重试！", 404, emailModels);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "错误！", 400, emailModels);
        }
        emailModels.add(email);
        return new Response(true, "成功！", 200, emailModels);
    }


    /**
     * 带有附件的邮件发送
     *
     * @param email
     * @return
     */
    @Override
    public Response file_sendEmail(EmailModel email, MultipartFile[] file,MultipartFile[] image,String url) {

        List<EmailModel> emailModels = new ArrayList<>();
        Useremail useremail = sendemailMapper.pop_keytostring(email.getUser_id(), email.getSend_email());
        if (useremail == null) {
            return new Response(false, "授权码不存在，请添加后重试！！！", 404, emailModels);
        }
        //2、确定协议的host端口 和类型是smtp或pop
        pop3_stmp smtpserver = sendemailMapper.pop_data(email.type_id, email.pop_id);

        try {
            boolean t =  sendMail.sendMails(email, smtpserver, useremail,file,image, 2);
//           javaMail javaMail=  new javaMail();
//           javaMail.sendEmail(email, smtpserver, useremail,file);
            /**
             * 存入数据
             */
            int state = 0;
            if (!t) {
                state = 2;
            } else {
                state = 1;
            }
            email.setState(state);
            email.setUrl(url);
            sendemailMapper.send_list(email);
            if (state == 2) {
                return new Response(false, "邮件发送失败，请重试！", 404, emailModels);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "错误！", 400, emailModels);
        }
        emailModels.add(email);
        return new Response(true, "成功！", 200, emailModels);
    }


    /**
     * 查询邮件发送历史记录
     *
     * @param id
     * @return
     */
    @Override
    public sendlistReuslt getlist_send(int id) {
        sendlistReuslt reuslt = new sendlistReuslt();
        try {
            UserInfo userInfo = sendemailMapper.getlist_seng(id);
            System.out.println(userInfo);
            if (userInfo == null) {
                reuslt.setMsg("用户不存在！");
                reuslt.setCode(402);
                reuslt.setSuc(false);
                return reuslt;
            } else {
                List<EmailModel> list = data_list(id);
                reuslt.setMsg("查询成功！");
                reuslt.setCode(200);
                reuslt.setSuc(true);
                reuslt.setUserInfo(userInfo);
                reuslt.setList(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            reuslt.setMsg("失败");
            reuslt.setCode(500);
            reuslt.setSuc(false);
            return reuslt;
        }
        return reuslt;
    }

    public List<EmailModel> data_list(int id) {
        //通过ID查询历史记录
        return sendemailMapper.data_list(id);
    }
}
