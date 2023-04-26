package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.Login;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Dto.UserDto;
import com.bridgelabz.BookstoreApplication.Dto.Verification;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Repository.UserRepository;

import java.util.List;

public interface UserService {
    List<UserData>getAllData();
    String register(UserDto userDto);
    String verification(Verification verification);
    String login(Login login);
    UserData getById(int id);
    UserData UpdateUser(int id, UserDto userDto);

    void delete(int id);

    UserData getdataByToken(String token);

    String deletedataByToken(String token);

    String forgotPassword(String email);

    String resetpassword(String email, String password, int otp);
}
