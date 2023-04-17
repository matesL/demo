package com.example.webreact.server.Imp;

import com.example.webreact.entity.uploadimage;
import com.example.webreact.mapper.UploadMapper;
import com.example.webreact.server.uploadServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class uploadServerImp implements uploadServer {
    @Autowired
    private UploadMapper uploadMapper;
    @Override
    public void save(uploadimage upload) {
        uploadMapper.save(upload);
    }
}
