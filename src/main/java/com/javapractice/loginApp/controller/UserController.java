package com.javapractice.loginApp.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javapractice.loginApp.model.User;
import com.javapractice.loginApp.service.UserService;


@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;    

    @GetMapping("/all-users")
    public List<User> getAll(){
        return userService.getAllUsers();
    }
    
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User myUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> users = userService.getByEmail(email);
        if(users.isPresent()){
            User user = users.get();
            user.setEmail(myUser.getEmail()!=null&& !myUser.getEmail().equals("")? myUser.getEmail():user.getEmail());
            user.setPassword(myUser.getPassword()!=null&& !myUser.getPassword().equals("")? myUser.getPassword():user.getPassword());

            userService.saveUser(myUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("an error occurred", HttpStatus.NO_CONTENT);
    }
    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> user = userService.getByEmail(email);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id){
        Optional<User> users =  userService.getById(id);
        if(users.isPresent()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{id}")
    public void deleteUser(@PathVariable ObjectId id){
        userService.deleteById(id);
    }
    @DeleteMapping("/delete")
    public void deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        userService.deleteByEmail(email);
    }


}
