package com.bridgelabz.BookstoreApplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue
    private int cart_id;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserData userData;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookModel bookModel;
    private int quantity;
    public CartModel(UserData userData, BookModel bookModel, int quantity) {
        this.userData = userData;
        this.bookModel = bookModel;
        this.quantity = quantity;
    }

}
