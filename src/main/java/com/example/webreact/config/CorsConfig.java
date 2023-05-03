package com.example.webreact.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

/**
 * @ClassName:CorsFilter
 * @Description: SpringBoot 跨域处理拦截器
 * 配置过滤器,解决跨域问题
 */

@Configuration
public class CorsConfig implements Filter{

    @CrossOrigin
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            System.out.println(request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "7200");
//            response.setHeader("Access-Control-Allow-Headers", "* ,Origin, Authorization,Token X-Requested-With, Content-Type, Accept,application/pdf,application/json");
            response.setHeader("Access-Control-Allow-Headers", "* ,Authorization");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Request-Headers","security-token");
            response.setHeader("token",request.getHeader("token"));

            // System.out.println(req+"jjj");
            chain.doFilter(req, res);

        }catch (Error e){
            e.printStackTrace();
        }

    }
    public void init(FilterConfig filterConfig) {
    }
    public void destroy() {
    }

}