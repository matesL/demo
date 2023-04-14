package com.example.webreact.controller;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.uploadimage;
import com.example.webreact.server.Imp.uploadServerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
    @CrossOrigin(origins = "*", allowedHeaders = "Content-Type")
    @GetMapping("/requestPDF")
    public ResponseEntity<StreamingResponseBody> postPdf() throws IOException {
        InputStream inputStream = new ClassPathResource("wuwj/wwj.pdf").getInputStream();

        // Set headers for dynamic loading of data
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("inline", "wwj.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        // Return input stream as response with headers
        StreamingResponseBody responseBody = outputStream -> {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        };
        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
    }
}
