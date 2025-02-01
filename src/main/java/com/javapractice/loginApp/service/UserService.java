package com.javapractice.loginApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javapractice.loginApp.model.User;
import com.javapractice.loginApp.repository.UserRepository;

import lombok.NonNull;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveUser(@NonNull User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            System.out.println("an error occurred while trying to create user"+ e);
            return false;
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void deleteById(ObjectId id){
         userRepository.deleteById(id);
    }
    public void deleteByEmail(String email){
        userRepository.deleteByEmail(email);
    }
    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
