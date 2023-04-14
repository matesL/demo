package com.example.webreact.controller;

import com.example.webreact.entity.Reslut.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class initializeController {
    @GetMapping ("/")
    public Response index() {
        return new Response(true, "成功！", 200);
    }

}
