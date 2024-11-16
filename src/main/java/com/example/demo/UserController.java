package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> allUsers() {
        return new ResponseEntity<List<Users>>(userService.allUsers(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public String postMethodName( String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}
