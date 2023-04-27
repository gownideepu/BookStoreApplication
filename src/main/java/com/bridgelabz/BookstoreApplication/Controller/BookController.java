package com.bridgelabz.BookstoreApplication.Controller;

import com.bridgelabz.BookstoreApplication.Dto.BookDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Dto.UserDto;
import com.bridgelabz.BookstoreApplication.Model.BookModel;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Service.BookService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseDto add(@RequestBody BookDto bookDto) {
        return bookService.addData(bookDto);
    }

    @PutMapping("Put/{id}")
    public BookModel updateBook(@PathVariable int id, @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @GetMapping("/getbook/{id}")
    public BookModel getById(@PathVariable int id) {
        return bookService.getById(id);
    }
    @GetMapping("/bookbyname")
    public List<BookModel> getBookByName(@RequestParam String name) {
        return bookService.getBookByName(name);
    }
    @GetMapping("/allbooks")
    public ResponseDto getAllData() {
        List<BookModel> books = bookService.getAllData();
        return new ResponseDto("The all Book Data",books);
    }
    @DeleteMapping("/deletebook/{id}")
    public ResponseDto Delete(@PathVariable int id) {
        return  bookService.delete(id);

    }
    @PutMapping("/changePrice")
    public String ChangeBookPrice(@RequestParam String token ,@RequestParam int id ,@RequestParam float price){
        return bookService.changeBookPrice(token,id,price);
    }
    @PutMapping("/changquantity/{token}")
    public String ChangeBookQuantity(@PathVariable String token ,@RequestParam int id ,@RequestParam int quantity){
        return bookService.changeBookQuantity(token,id,quantity);
    }

}