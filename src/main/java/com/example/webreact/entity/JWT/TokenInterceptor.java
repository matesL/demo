

package com.example.webreact.entity.JWT;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;


/**
 * @author laz
 * @date 2022/09/09 14:56
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
public static String token="";
    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response,  Object handler) throws Exception {

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        //获取到token
         token = request.getHeader("Authorization");
        System.out.println(request.getHeader("Authorization"));
        if (token!=null){
            boolean result= TokenUtil.verify(token);
            if (result){
                System.out.println("通过拦截器");
                return true;
            }

        }



        try {
            JSONObject json=new JSONObject();
            json.put("msg","token verify fail");
            json.put("code","500");
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器");
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
