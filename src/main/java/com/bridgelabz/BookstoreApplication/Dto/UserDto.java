package com.bridgelabz.BookstoreApplication.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message = "The Name is notEmpty")
    private String firstName;
    @NotEmpty(message = "The LastName is not empty" )
    private String lastName;
    private String kyc;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate registeredDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedDate;
    @NotEmpty(message = "the password is not empty")
    private String password;
    @NotEmpty(message = "The email is not empty")
    private String email;
    private int otp;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate purchaseDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;
}
