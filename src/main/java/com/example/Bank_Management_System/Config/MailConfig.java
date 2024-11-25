package com.example.Bank_Management_System.Config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Bean
    public static JavaMailSenderImpl getMailConfig(){
        JavaMailSenderImpl emailConfig = new JavaMailSenderImpl();

       // Set Properties for Gmail SMTP:
       Properties props = emailConfig.getJavaMailProperties();
       props.put("mail.transport.protocol", "smtp");
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.debug", "true");

       // Set Mail Credentials (Gmail):
       emailConfig.setHost("smtp.gmail.com");
       emailConfig.setPort(587);
       emailConfig.setUsername("noreply9823@gmail.com");
       emailConfig.setPassword("ygix qeri tpqi hygu"); 

        return emailConfig;
    }
}
