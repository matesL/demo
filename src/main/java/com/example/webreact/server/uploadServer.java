package com.example.webreact.server;

import com.example.webreact.entity.uploadimage;
import org.springframework.stereotype.Service;


@Service
public interface uploadServer {
    void save(uploadimage upload);
}

