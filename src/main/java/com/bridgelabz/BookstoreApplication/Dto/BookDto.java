package com.bridgelabz.BookstoreApplication.Dto;

import com.bridgelabz.BookstoreApplication.Model.BookModel;
import jakarta.mail.Multipart;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message = "The Name is notEmpty")
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private float bookPrice;
    private long bookQuantity;


}
