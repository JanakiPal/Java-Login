package com.javapractice.loginApp.repository;

import com.javapractice.loginApp.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    void deleteByEmail(String email);
    Optional<User> findByEmail(String email);
}
