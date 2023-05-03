package com.example.webreact.server.Imp;

import com.example.webreact.entity.uploadimage;
import com.example.webreact.mapper.UploadMapper;
import com.example.webreact.server.uploadServer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Component
@Service
public class uploadServerImp implements uploadServer {
    @Autowired
    private UploadMapper uploadMapper;
    @Override
    public void save(uploadimage upload) {

        uploadMapper.save(upload);
    }
    @Override
    public String upload_pic(MultipartFile[] files, HttpServletRequest req,int id) {
        String url = null;
        if (files == null) {
            return "";
        }
        for (MultipartFile file : files) {

            //            获取文件名
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                System.out.println(fileName + "dddd");
            }
            String storeName = null;
            if (fileName != null) {
                storeName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
            }

            try {
//                System.out.println("文件名: " + fileName + "\n上传时间: " + uploadTime + "\n长度:" + logs + " \n说明:" + country + "\n路径名:" + storeName);
                String uploadTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String filepath = "uploads/" + uploadTime;

                Path filePath = Paths.get(filepath, storeName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
                storeName = "/" + filePath;
                url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + storeName;
                uploadMapper.save(new uploadimage(fileName, url, id, "邮件图片", uploadTime));

            } catch (Exception e) {
                e.printStackTrace();
                return url;
            }
        }
        return url;
    }
}
