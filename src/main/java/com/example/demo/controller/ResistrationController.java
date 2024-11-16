package com.example.demo.controller;

import com.example.demo.Service.RegistrationServices;
import com.example.demo.Users;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/UserRegistration")
public class ResistrationController {

    @Autowired
    public RegistrationServices registrationServices;

  @PostMapping()
  public ResponseEntity Registration(@Valid @RequestBody Users request) throws Exception {
      //TODO: process POST request


      
      return registrationServices.RegisterUser(request);
  }
  @GetMapping("/emailVarification")
    public ResponseEntity<String> emailVarification(@RequestParam String token) throws IllegalAccessException {

      return ResponseEntity.ok(" "+registrationServices.ConfirmMail(token));
  }
  

}
