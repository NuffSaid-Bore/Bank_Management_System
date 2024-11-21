package com.example.Bank_Management_System.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        System.out.println("In Index Page Controller");
        return getIndexPage;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView getRegisterPage = new ModelAndView("register");
        getRegisterPage.addObject("PageTitle", "Register");
        System.out.println("In Register Page Controller");
        return getRegisterPage;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView getLoginPage = new ModelAndView("login");
        getLoginPage.addObject("PageTitle", "Login");
        System.out.println("In Login Page Controller");
        return getLoginPage;
    }

    @GetMapping("/error")
    public ModelAndView getError(){
        ModelAndView getErrorPage = new ModelAndView("error");
        getErrorPage.addObject("PageTitle", "Errors");
        System.out.println("In Error Page Controller");
        return getErrorPage;
    }

     @GetMapping("/verify")
    public ModelAndView getVerify(@RequestParam("token")String token, @RequestParam("code") String code){
        // Set View:
        ModelAndView getVerifyPage;

        // // Get Token In Database:
        // String dbToken = userRepository.checkToken(token);

        // // Check If Token Is Valid:
        // if(dbToken == null){
            getVerifyPage  = new ModelAndView("error");
        //     getVerifyPage.addObject("error", "This Session Has Expired");
        //     return  getVerifyPage;
        // }
        // // End Of Check If Token Is Valid.

        // // Update and Verify Account:
        // userRepository.verifyAccount(token, code);

        // getVerifyPage = new ModelAndView("login");

        // getVerifyPage.addObject("success", "Account Verified Successfully, Please proceed to Log In!");

        System.out.println("In Verify Account Controller");
        return getVerifyPage;
    }
    
}
