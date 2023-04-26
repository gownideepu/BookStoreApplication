package com.bridgelabz.BookstoreApplication.Model;

import com.bridgelabz.BookstoreApplication.Dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String kyc;
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    private String password;
    private String email;
    private boolean verify;
    private int otp;
    private boolean verifyOtp;
    private String token;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;

    public void updateData(UserDto userDto){
        this.firstName= userDto.getFirstName();
        this.lastName= userDto.getLastName();
        this.kyc=userDto.getKyc();
        this.dob=userDto.getDob();
        this.password=userDto.getPassword();
        this.registeredDate=userDto.getRegisteredDate();
        this.updatedDate=userDto.getUpdatedDate();
        this.email=userDto.getEmail();
        this.purchaseDate=userDto.getPurchaseDate();
        this.expiryDate=userDto.getExpiryDate();
        this.otp=userDto.getOtp();

    }
    public UserData(UserDto userDto){
        this.updateData(userDto);
    }
}
