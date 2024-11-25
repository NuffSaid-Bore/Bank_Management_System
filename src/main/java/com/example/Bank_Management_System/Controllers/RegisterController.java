package com.example.Bank_Management_System.Controllers;
import com.example.Bank_Management_System.helpers.Token;
import com.example.Bank_Management_System.mailMessenger.MailMessenger;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Bank_Management_System.helpers.HTML;
import com.example.Bank_Management_System.models.User;
import com.example.Bank_Management_System.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView getRegisterPage = new ModelAndView("register");
        getRegisterPage.addObject("PageTitle", "Register");
        getRegisterPage.addObject("registerUser", new User());
        System.out.println("In Register Page Controller");
        return getRegisterPage;
    }
    
    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("registerUser") User user, 
                                 BindingResult result,
                                 @RequestParam String confirm_password) throws MessagingException {
    
        ModelAndView registrationPage = new ModelAndView("register");
    
        // Check for form validation errors
        if (result.hasErrors()) {
            return registrationPage;
        }
    
        // Check for password mismatch
        if (!user.getPassword().equals(confirm_password)) {
            registrationPage.addObject("passwordMisMatch", "Passwords do not match");
            return registrationPage;
        }
    
        // Token generation and email logic
        String token = Token.generateToken();
        Random rand = new Random();
        int bound = 123;
        int code = bound * rand.nextInt(bound);
    
        // Get the email body content for verification
        String emailBody = HTML.htmlEmailTemplate(token, code);
    
        // Hash the password before saving
        String hashed_password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    
        // Register the user in the database
        userRepository.registerUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), 
                                    user.getId_num(), hashed_password, token, code);
    
        // Send the email verification message
        MailMessenger.htmlEmailMessenger("noreply9823@gmail.com", user.getEmail(), "Verify Account", emailBody);
    
        // Success message
        String successMessage = "Account Registered Successfully! Please Check your Email and Verify your Account.";
        registrationPage.addObject("success", successMessage);
        return registrationPage;
    }
    
    
    
}
