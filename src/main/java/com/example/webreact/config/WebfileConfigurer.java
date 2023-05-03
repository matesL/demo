package com.example.webreact.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebfileConfigurer implements WebMvcConfigurer {
  @Value("file.name.path")
  private  static  String filepath;
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("uploads/**") //资源访问路径
                    .addResourceLocations("file:" + "/Users/lucky/Desktop/毕业论文/代码/admin/webreact/uploads/","classpath:uploads/**"); // 图片存储路径
        }

}

