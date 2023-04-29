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

    @PutMapping("/Put/{book_id}")
    public BookModel updateBook(@PathVariable int book_id, @RequestBody BookDto bookDto) {
        return bookService.updateBook(book_id, bookDto);
    }
    @GetMapping("/getbook/{book_id}")
    public BookModel getById(@PathVariable int book_id) {
        return bookService.getById(book_id);
    }
    @GetMapping("/bookbyname")
    public List<BookModel> getBookByName(@RequestParam String name) {
        return bookService.getBookByName(name);
    }
    @GetMapping("/getallbooks")
    public ResponseDto getAllData() {
        List<BookModel> books = bookService.getAllData();
        return new ResponseDto("The all Book Data",books);
    }
    @DeleteMapping("/deletebook/{book_id}")
    public ResponseDto Delete(@PathVariable int book_id) {
        return  bookService.delete(book_id);

    }
    @PutMapping("/changePrice")
    public String ChangeBookPrice(@RequestParam String token ,@RequestParam int book_id ,@RequestParam float price){
        return bookService.changeBookPrice(token,book_id,price);
    }
    @PutMapping("/changequantity/{token}")
    public String ChangeBookQuantity(@PathVariable String token ,@RequestParam int book_id ,@RequestParam int quantity){
        return bookService.changeBookQuantity(token,book_id,quantity);
    }
}