package com.bridgelabz.BookstoreApplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue
    private int order_id;
    private LocalDate orderDate;
    private int quantity;
    private Float price;
    private String address;
    private int user_id;
    private float totalPrice;

    @JsonIgnoreProperties(value = {"application", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookModel bookModel;
    private Boolean cancel = false;
    public OrderModel(int user_id,int quantity,BookModel bookModel,Float price,String address,float totalPrice,LocalDate orderDate){
        this.user_id=user_id;
        this.quantity=quantity;
        this.bookModel=bookModel;
        this.price=price;
        this.address=address;
        this.totalPrice=totalPrice;
        this.orderDate=orderDate;
    }
}

