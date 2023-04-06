package com.example.webreact.controller;


import com.example.webreact.controller.GZip.compressder;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.uploadimage;
import com.example.webreact.server.Imp.uploadServerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping(value = "/api")
public class uploadfilesController {
    @Autowired
    private uploadServerImp uploadServerImp;

    /**
     * @param  files 图片列表
     * @return   result
     */
    @PostMapping("/upload")
    public Response upload(@RequestParam("image") MultipartFile[] files) {

        for (MultipartFile file : files) {
            //            获取文件名
            String fileName = file.getOriginalFilename();
            //创建文件对象
            String storeName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
            String uploadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logs = String.valueOf(file.getSize());
            String country = String.valueOf(fileName.lastIndexOf("."));

            try {
//                System.out.println("文件名: " + fileName + "\n上传时间: " + uploadTime + "\n长度:" + logs + " \n说明:" + country + "\n路径名:" + storeName);

                Path filePath = Paths.get("uploads", storeName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());

                storeName = "/uploads/" + storeName;
                uploadServerImp.save(new uploadimage(fileName, storeName, logs, country, uploadTime));
            } catch (Exception e) {
                e.printStackTrace();
                return new Response(false, "上传失败", 404);
            }
        }

        return new Response(true, "上传成功！", 200);
    }
    /**
     * @return
     */
    // Define endpoint for handling PDF files
    @PostMapping("/requestPDF")
    public ResponseEntity<String> postPdf() throws IOException {
        // Read PDF file into a byte array
        try {
            File file = new File("src/main/resources/wuwj/wwj.pdf");
            byte[] bytes = Files.readAllBytes(file.toPath());

            // Set headers for dynamic loading of data
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("inline", file.getName());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            compressder compressder = new compressder();
            // Compress the byte array to reduce file size
            bytes = compressder.compress(bytes);
            System.out.println(Arrays.toString(bytes));
            // Return byte array as response with headers
            String encodedPdf = Base64.getEncoder().encodeToString(bytes);
            // 使用断点传输
            headers.set("Content-Transfer-Encoding", "binary");
            return new ResponseEntity<>(encodedPdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
