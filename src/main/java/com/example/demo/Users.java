package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {



    @Id
    private ObjectId id;

    @NotNull(message = "username can't be null")
    private String username;
    @NotNull()
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Must be at least 8 characters long.\n" +
            "Must contain at least one uppercase letter (A-Z).\n" +
            "Must contain at least one lowercase letter (a-z).\n" +
            "Must contain at least one digit (0-9).\n" +
            "Must contain at least one special character (e.g., @, $, !, %, *, #, ?, &).")
    private String password;
    @Size(min = 3,message = "minimum value length must be three")
    private String firstName;
    private String lastName;
    @NotNull
    @Email(message = "Email is not valid")
    private String email;
}
