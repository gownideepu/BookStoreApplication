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
    public BookModel updateBook(int book_id, BookDto bookDto) {
        BookModel bookModel=this.getById(book_id);
        bookModel.updateData(bookDto);
        return bookRepository.save(bookModel);
    }

    @Override
    public BookModel getById(int book_id) {
        return bookRepository.findById(book_id).orElseThrow(() -> new BookStoreCustomException(" Data Not found .. wih id: "+ book_id));
    }

    @Override
    public ResponseDto delete(int book_id) {
        BookModel book=this.getById(book_id);
        bookRepository.deleteById(book_id);
        return new ResponseDto("The Data has deleted", book_id);
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
    public String changeBookPrice(String token, int book_id, float price) {
        int useId=jwtToken.decodeToken(token);
        Optional<UserData> user = userRepository.findById(useId);
        if(user!=null){
            Optional<BookModel> bookStore=bookRepository.findById(book_id);
            bookStore.get().setBookPrice(price);
            bookRepository.save(bookStore.get());
            return "Ther Price changed.........";
        }
        else {
            return "The Price Cant changed";
        }
    }
    @Override
    public String changeBookQuantity(String token, int book_id, int quantity) {
        int useId=jwtToken.decodeToken(token);
        Optional<UserData> user = userRepository.findById(useId);
        System.out.println("user "+user);
        if(user!=null){
            Optional<BookModel> bookStore=bookRepository.findById(book_id);
            bookStore.get().setBookQuantity(quantity);
            bookRepository.save(bookStore.get());
            return "Ther quantity changed.........";
        }
        else {
            return "The quantity Cant changed";
        }
    }
}