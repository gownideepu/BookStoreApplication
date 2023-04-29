package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.CartDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Model.CartModel;

import java.util.List;

public interface CartService {
    ResponseDto addCart(CartDto cartDto);

    ResponseDto removeCartById(int cartId);

    CartModel getById(int cartId);

    ResponseDto getCartByToken(String token);

    ResponseDto updateBytoken(String token, int cartId, int quantity);

    List<CartModel> getAlldata();
}
