package com.bridgelabz.BookstoreApplication.Dto;

import lombok.Data;

@Data
public class CartDto {
    public String token;
    public int book_id;
    public int quantity;
}
