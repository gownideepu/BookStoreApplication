package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.BookDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Model.BookModel;
import com.bridgelabz.BookstoreApplication.Model.UserData;

import java.util.List;

public interface BookService {


    ResponseDto addData(BookDto bookDto);

    BookModel updateBook(int id, BookDto bookDto);

    BookModel getById(int id);

    ResponseDto delete(int id);

    List<BookModel> getAllData();

    List<BookModel> getBookByName(String name);

    String changeBookPrice(String token, int id, float price);

    String changeBookQuantity(String token, int id, int quantity);
}
