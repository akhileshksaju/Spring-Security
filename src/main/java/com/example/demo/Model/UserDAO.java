package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {
  @Id
    private ObjectId id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
