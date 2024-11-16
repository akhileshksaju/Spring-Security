package com.example.demo.Service;

import com.example.demo.Model.UserDAO;
import com.example.demo.Model.UserPrincipal;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDAO user = repository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
