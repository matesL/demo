package com.example.webreact.server;

import com.alibaba.fastjson2.JSONObject;
import com.example.webreact.entity.uploadimage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface uploadServer {
    void save(uploadimage upload);

    List upload_pic(MultipartFile[] files, HttpServletRequest req, int id);
}

