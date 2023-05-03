package com.example.webreact.server;

import com.example.webreact.entity.uploadimage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface uploadServer {
    void save(uploadimage upload);

    String upload_pic(MultipartFile[] files, HttpServletRequest req,int id);
}

