package com.example.demo.Model;

import com.example.demo.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Data
@Document(collection = "email_confirmation_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailConfirmationToken {

    @Id
    private ObjectId id;  // MongoDB will automatically generate an ObjectId for this field

    private String token;
    @Indexed(expireAfter = "10s")
    private String userEmail;
    private Long expiresAt;// This could be an embedded reference to a User object (as you are associating with Users)
    private Users user;
}
