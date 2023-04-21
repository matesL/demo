package com.example.webreact.server.Imp.emailImp;

import com.example.webreact.entity.JWT.TokenInterceptor;
import com.example.webreact.entity.JWT.TokenUtil;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.mail.Email.EmailModel;
import com.example.webreact.mail.Email.Useremail;
import com.example.webreact.mail.JavaSocket;
import com.example.webreact.server.Email.sendEmailServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class sendemailSericeImp implements sendEmailServer {


@Autowired
private com.example.webreact.mapper.post.sendemailMapper sendemailMapper;
    /**
     * 存入数据
     * @param
     * @return
     */
    /**
     * state 表示邮箱发送状态 1 成功  2 失败 0 代发送
     */
    private static  int state=0;
    @Override
    public Response send_list(Useremail useremail) {

//通过用token获取请求ID，将发送记录存入数据库
//TokenUtil.getUserID(token)
            try{
                String token = TokenInterceptor.token;
                EmailModel model=new EmailModel();

                model.setMessage(useremail.getMessage());
                model.setTo_email(useremail.getTo_email());
                model.setUser_id(TokenUtil.getUserID(token));
                model.setSend_email(useremail.getSend_email());
               boolean t=  JavaSocket.sendMail(model,1);
                if (!t) {
                    state=2;
                }
                else {
                    state=1;
                }
                System.out.println(state+"  state");
                model.setState(state);
                model.setSendtime("2023-04-22");
                model.setUrl("djdjdj");
                model.setTitle(useremail.getMessage());
               int raulst= sendemailMapper.send_list(model);
                if (raulst >= 1) {
                    return new Response(true,"成功！",200);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return new Response(false,"错误！",400);
            }

        return new Response(true,"成功！",200);
    }

    public boolean send(){

        return false;
    }
}
