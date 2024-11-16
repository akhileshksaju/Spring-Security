package com.example.demo.Service;

import com.example.demo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    public String Verify(Users users){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(),users.getPassword()));
        return authentication.isAuthenticated()?jwtService.generateToken(users.getEmail()):"no";
    }
}
