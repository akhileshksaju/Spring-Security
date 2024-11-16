package com.example.demo.Service;


import com.example.demo.Model.EmailConfirmationToken;
import com.example.demo.Repo.EmailConfirmationTokenRepo;
import com.example.demo.UserRepository;
import com.example.demo.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class RegistrationServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailConfirmationTokenRepo emailConfirmationTokenRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.localhost}")
    public String localHostUrl;

    @Value("${app.mail.from}")
    public String from;

    @Value("${app.mail.subject}")
    public String subject;




    public ResponseEntity RegisterUser(Users user) throws Exception {

        if(userRepository.findByEmail(user.getEmail())!=null){
            return new ResponseEntity<>("user already exist", HttpStatus.CONFLICT);
        }





        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        EmailConfirmationToken emailConfirmationToken = registrationConfirmationEmailTokenGenerator(user);
        if(emailConfirmationTokenRepo.findByUserEmail(emailConfirmationToken.getUserEmail())!= null){
            emailConfirmationTokenRepo.deleteByUserEmail(emailConfirmationToken.getUserEmail());
        }
        EmailSender(emailConfirmationToken);



        //userRepository.save(user);


        return ResponseEntity.ok("done");
    }

    public EmailConfirmationToken registrationConfirmationEmailTokenGenerator(@org.jetbrains.annotations.NotNull Users user){

        return EmailConfirmationToken.builder()
                .userEmail(user.getEmail())
                .expiresAt(System.currentTimeMillis()+2*60000)
                .token(jwtService.generateToken(user.getEmail()))
                .user(user)
                .build();

    }

    public Boolean EmailSender(EmailConfirmationToken emailConfirmationToken) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(emailConfirmationToken.getUserEmail());
            mimeMessageHelper.setSubject(subject);

            // Ensure the HTML file exists before using it
            try (var inputStream = getClass().getClassLoader().getResourceAsStream("HTMLCode\\MailHtml.html")) {
                if (inputStream == null) {
                    throw new IOException("HTML file not found.");
                }
                String emailContent=new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                emailContent = emailContent.replace("${verificationLink}",localHostUrl+jwtService.generateToken(emailConfirmationToken.getUserEmail()));
                mimeMessageHelper.setText(emailContent,true);
            }

            mailSender.send(mimeMessage);
            emailConfirmationTokenRepo.save(emailConfirmationToken);
            return true;

        } catch (MessagingException | IOException | MailException e) {
            // You can log the exception or rethrow a custom exception if necessary
            e.printStackTrace(); // logging exception for debugging
            throw new RuntimeException("Error sending email: " + e.getMessage(), e);
        }
    }


    public String ConfirmMail(String token) throws IllegalAccessException {
        EmailConfirmationToken emailConfirmationToken1=null;
        try{
           emailConfirmationToken1 = emailConfirmationTokenRepo.findByToken(token);
           if(emailConfirmationToken1 !=null){
               Users user = emailConfirmationToken1.getUser();
               userRepository.save(user);
               emailConfirmationTokenRepo.delete(emailConfirmationToken1);
               return("Success");

           }
       }catch (Exception e){
           e.printStackTrace();
       }
       finally {
           if(emailConfirmationTokenRepo.findByToken(token) != null){
               assert emailConfirmationToken1 != null;
               emailConfirmationTokenRepo.delete(emailConfirmationToken1);
           }
       }
        return ("Failed");

    }
}
