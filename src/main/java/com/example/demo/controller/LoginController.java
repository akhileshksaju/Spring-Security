package com.example.demo.controller;

import com.example.demo.Service.LoginService;
import com.example.demo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class LoginController {


    @Autowired
    private LoginService loginService;



    @PostMapping("/login")
    public ResponseEntity<String> LoginLine(@RequestBody Users users) throws Exception{

        return ResponseEntity.ok(loginService.Verify(users));
    }
}
