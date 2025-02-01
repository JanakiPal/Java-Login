package com.javapractice.loginApp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "userDetails")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique=true)
    @Field("email")
    private String email;
    @NonNull
    private String password;

}