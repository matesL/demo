
package com.example.webreact.entity.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author laz
 * @date 2022/09/09 13:56
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 配置拦截器、拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了登录、注册，其他都拦截
        excludePath.add("/v2/**");

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")

                .excludePathPatterns(excludePath);

        WebMvcConfigurer.super.addInterceptors(registry);

    }

}