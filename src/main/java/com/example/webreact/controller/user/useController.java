package com.example.webreact.controller.user;

import com.example.webreact.entity.basecat.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
public class useController {
    @PostMapping("/login")
    public UserInfo login(UserInfo userInfo){

        return  userInfo;
    }
}
