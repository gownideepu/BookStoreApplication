package com.bridgelabz.BookstoreApplication.Controller;

import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Dto.UserDto;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Repository.UserRepository;
import com.bridgelabz.BookstoreApplication.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MyController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseDto getDataById(@PathVariable int id){
        UserData  userData= userService.getById(id);
        ResponseDto responseDto=new ResponseDto("Data is ",userData);
        return responseDto;
    }
    @GetMapping("/")
    public ResponseDto getAllData() {
        List<UserData> data = userService.getAllData();
        ResponseDto responseDto = new ResponseDto("The All Employees ", data);
        return responseDto;
    }
    @PutMapping("/update/{id}")
    public ResponseDto update(@RequestBody UserDto userDto, @PathVariable int id) {
        UserData userData = userService.UpdateUser(id, userDto);
        ResponseDto responseDto = new ResponseDto("Data is", userData);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable int id){
        userService.delete(id);
        return "Deleted the data from the id"+id;
    }
    @GetMapping("/token/{token}")
    public ResponseEntity<ResponseDto> getDataByToken(@PathVariable String token){
        UserData userData=userService.getdataByToken(token);
        ResponseDto responseDto = new ResponseDto("Data for the token is->",userData);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/deletetoken/{token}")
    public ResponseEntity<ResponseDto> deleteDataByToken(@PathVariable String token){
        String userData=userService.deletedataByToken(token);
        ResponseDto responseDto = new ResponseDto("Data is deleted",userData);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
