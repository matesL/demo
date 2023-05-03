package com.example.webreact.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.webreact.entity.Reslut.Response;
import com.example.webreact.entity.uploadimage;
import com.example.webreact.server.Imp.uploadServerImp;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.*;


@RestController
@RequestMapping(value = "/files")
public class uploadfilesController {
    @Autowired
    private uploadServerImp uploadServerImp;

    /**
     * @param  files 图片列表
     * @return   result
     */
    @PostMapping("/upload")
    public Response upload(@RequestParam("image") MultipartFile[] files, int id, HttpServletRequest req) {

        String url = null;
        String uploadTime = "";
        Map<String,Object> data=new HashMap<>();
        System.out.println(files[0]+"  "+"ssss");
        if (files[0]==null)return new Response(false, "上传失败！", 404,null);
        for (MultipartFile file : files) {

            //            获取文件名
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                System.out.println(fileName+"dddd");
            }
            //创建文件对象
            String storeName = null;
            if (fileName != null) {
                storeName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
            }
            uploadTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String logs = String.valueOf(file.getSize());
            String country = String.valueOf(fileName.lastIndexOf("."));

            try {
//                System.out.println("文件名: " + fileName + "\n上传时间: " + uploadTime + "\n长度:" + logs + " \n说明:" + country + "\n路径名:" + storeName);
                String filepath = "uploads/" + uploadTime;
                Path filePath = Paths.get(filepath, storeName);
                System.out.println(filePath);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());

                storeName ="/"+ String.valueOf(filePath);
                url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + storeName;
                uploadServerImp.save(new uploadimage(fileName, url,id , country, uploadTime));

            } catch (Exception e) {
                e.printStackTrace();

                return new Response(false, "上传失败！", 404,null);
            }
        }
        data.put("url",url);
        data.put("time",uploadTime);
        JSONObject json = new JSONObject(data);
        List<Map> list=new ArrayList<>();
        list.add(json);
//        String jsonString2= JSON.toJSONString(data);
        System.out.println(list);
        return new Response(true, "上传成功！", 200, list);
    }

    /**
     * @return
     */
    // Define endpoint for handling PDF files
    @CrossOrigin(origins = "*", allowedHeaders = "Content-Type")
    @GetMapping("/requestPDF")
    public ResponseEntity<StreamingResponseBody> postPdf( String name) throws IOException {

            try {
                    InputStream inputStream = new ClassPathResource("uploads/16c069d34edb4277b1a12f6397397b6f.jpg").getInputStream();
                    // Set headers for dynamic loading of data
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.parseMediaType("*"));
                    headers.setContentDispositionFormData("inline", "16c069d34edb4277b1a12f6397397b6f.jpg");
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
                    } catch (Error error){
               return null;
            }

    }
}
