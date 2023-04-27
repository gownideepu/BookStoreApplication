package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.BookDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Exception.BookStoreCustomException;
import com.bridgelabz.BookstoreApplication.Model.BookModel;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Repository.BookRepository;
import com.bridgelabz.BookstoreApplication.Repository.UserRepository;
import com.bridgelabz.BookstoreApplication.Util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDto addData(BookDto bookDto) {
        BookModel bookModel=new BookModel(bookDto);
        bookRepository.save(bookModel);
        return new ResponseDto("The Data added", bookModel);
    }

    @Override
    public BookModel updateBook(int id, BookDto bookDto) {
        BookModel bookModel=this.getById(id);
        bookModel.updateData(bookDto);
        return bookRepository.save(bookModel);
    }

    @Override
    public BookModel getById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookStoreCustomException(" Data Not found .. wih id: "+ id));
    }

    @Override
    public ResponseDto delete(int id) {
        BookModel book=this.getById(id);
        bookRepository.deleteById(id);
        return new ResponseDto("The Data has deleted", id);
    }

    @Override
    public List<BookModel> getAllData() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookModel> getBookByName(String name) {
        List<BookModel> books =bookRepository.getBookByName(name);
        for (Object x:books) {
            System.out.println("The books "+x);
        }
        return books;
    }

    @Override
    public String changeBookPrice(String token, int id, float price) {
        int useId=jwtToken.decodeToken(token);
        Optional<UserData> user = userRepository.findById(useId);
        if(user!=null){
            Optional<BookModel> bookStore=bookRepository.findById(id);
            bookStore.get().setBookPrice(price);
            bookRepository.save(bookStore.get());
            return "Ther Price changed.........";
        }
        else {
            return "The Price Cant changed";
        }
    }
    @Override
    public String changeBookQuantity(String token, int id, int quantity) {
        int useId=jwtToken.decodeToken(token);
        Optional<UserData> user = userRepository.findById(useId);
        System.out.println("user "+user);
        if(user!=null){
            Optional<BookModel> bookStore=bookRepository.findById(id);
            bookStore.get().setBookQuantity(quantity);
            bookRepository.save(bookStore.get());
            return "Ther quantity changed.........";
        }
        else {
            return "The quantity Cant changed";
        }
    }
}