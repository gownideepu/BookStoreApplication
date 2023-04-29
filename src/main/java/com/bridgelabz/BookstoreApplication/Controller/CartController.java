package com.bridgelabz.BookstoreApplication.Controller;

import com.bridgelabz.BookstoreApplication.Dto.CartDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Model.CartModel;
import com.bridgelabz.BookstoreApplication.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    public ResponseDto addCart(@RequestBody CartDto cartDto){
        return cartService.addCart(cartDto);
    }
    @DeleteMapping("/deleteCart/{cart_id}")
    public ResponseDto removeCartById(@PathVariable int cart_id){
        return cartService.removeCartById(cart_id);
    }
    @GetMapping("/getCart/{cart_id}")
    public CartModel getCartById(@PathVariable int cart_id){
        return cartService.getById(cart_id);
    }
    @GetMapping("/getCart/{token}")
    public ResponseDto getCartByToken(@PathVariable("token")  String token){
        return cartService.getCartByToken(token);
    }
    @PutMapping("/updateCart/{token}")
    public  ResponseDto updateQuantity(@PathVariable("token") String token,@RequestParam int cart_id, @RequestParam int quantity){
        return cartService.updateBytoken(token,cart_id,quantity);

    }
    @GetMapping("/getAllCartData")
    public List<CartModel> getAllData(){
        return cartService.getAlldata();
    }
}
