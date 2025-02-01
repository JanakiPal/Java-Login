package com.javapractice.loginApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javapractice.loginApp.model.User;
import com.javapractice.loginApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/check")
    public String check(){
        return "ok";
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User userData){
        System.out.println(userData.getEmail());
        System.out.println(userData.getPassword());
        boolean  userCreated = userService.saveUser(userData);
        if(userCreated){
            return new ResponseEntity<>(userData, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
