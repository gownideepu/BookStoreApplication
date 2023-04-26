package com.bridgelabz.BookstoreApplication.Controller;

import com.bridgelabz.BookstoreApplication.Dto.Login;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Dto.UserDto;
import com.bridgelabz.BookstoreApplication.Dto.Verification;
import com.bridgelabz.BookstoreApplication.Service.UserService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @PutMapping("/Verify")
    public String verification(@RequestBody Verification verification) {
        return userService.verification(verification);
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        return userService.login(login);
    }

    @PostMapping("/forgot")
    public String forgotPassword(@RequestParam String email) {
        return userService.forgotPassword(email);
    }

    @PutMapping("/reset")
    public String resetPassword(@RequestParam String email, @RequestParam String password,@RequestParam int otp) {
        return userService.resetpassword(email, password,otp);
    }
}