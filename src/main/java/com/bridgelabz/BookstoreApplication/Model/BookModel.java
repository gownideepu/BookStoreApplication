package com.bridgelabz.BookstoreApplication.Model;

import com.bridgelabz.BookstoreApplication.Dto.BookDto;
import jakarta.mail.Multipart;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookModel {
    @Id
    @GeneratedValue
    private int id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private float bookPrice;
    private long bookQuantity;
    public void updateData(BookDto bookDto){
        this.bookName=bookDto.getBookName();
        this.bookAuthor=bookDto.getBookAuthor();
        this.bookDescription=bookDto.getBookDescription();
        this.bookLogo=bookDto.getBookLogo();
        this.bookPrice=bookDto.getBookPrice();
        this.bookQuantity=bookDto.getBookQuantity();
    }
    public BookModel(BookDto bookDto){
        this.updateData(bookDto);
    }
}
