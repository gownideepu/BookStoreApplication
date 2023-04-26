package com.bridgelabz.BookstoreApplication.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage simpleEmailMessage=new SimpleMailMessage();
        simpleEmailMessage.setFrom("gownideepthi1103@gmail.com");
        simpleEmailMessage.setTo(toEmail);
        simpleEmailMessage.setText(body);
        simpleEmailMessage.setSubject(subject);
        mailSender.send(simpleEmailMessage);
        System.out.println("Mail sent Successfully!!");
    }
}
