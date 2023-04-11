package com.example.webreact.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName:  CorsFilter
 * @Description: SpringBoot 跨域处理拦截器
 */
/**
        * 配置过滤器,解决跨域问题
        */
@Configuration
public class CorsConfig implements Filter{

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Token,application/pdf");
            response.setHeader("Access-Control-Allow-Credentials","true");
            System.out.println(req+"jjj");
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