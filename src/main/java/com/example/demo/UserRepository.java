package com.example.demo;

import com.example.demo.Model.UserDAO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, ObjectId> {

//    Users findByUsername(String username);
    UserDAO findByEmail(String email);


}
