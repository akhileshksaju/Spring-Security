package com.example.demo.Repo;

import com.example.demo.Model.EmailConfirmationToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfirmationTokenRepo extends MongoRepository<EmailConfirmationToken, ObjectId> {

        EmailConfirmationToken findByUserEmail(String email);
        EmailConfirmationToken findByToken(String token);
        void deleteByUserEmail(String email);



}
