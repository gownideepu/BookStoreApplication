package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.CartDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Exception.BookStoreCustomException;
import com.bridgelabz.BookstoreApplication.Model.BookModel;
import com.bridgelabz.BookstoreApplication.Model.CartModel;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Repository.CartRepository;
import com.bridgelabz.BookstoreApplication.Repository.UserRepository;
import com.bridgelabz.BookstoreApplication.Util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponseDto addCart(CartDto cartDto) {
        int user_id=jwtToken.decodeToken(cartDto.getToken());
        Optional<UserData> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            BookModel book = bookService.getById(cartDto.getBook_id());
            CartModel cartModel = new CartModel(user.get(), book, cartDto.quantity);
            cartRepository.save(cartModel);
            return new ResponseDto("", cartModel);
        } else {
            return new ResponseDto("The cart is not added ", " ");
        }
    }
    @Override
    public ResponseDto removeCartById(int cartId) {
        cartRepository.deleteById(cartId);
        return new ResponseDto("The Cart deleted ", "id" + cartId);
    }

    @Override
    public CartModel getById(int cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new BookStoreCustomException(" Data Not found .. wih id: " + cartId));
    }

    @Override
    public ResponseDto getCartByToken(String token) {
        int id = jwtToken.decodeToken(token);
        System.out.println(id + "  id");
        int cartid = cartRepository.findIdByUserId(id);
        System.out.println(cartid + "cart id");
        if (cartid == 0) {
            return new ResponseDto("the data ", userRepository.findById(cartid));
        } else {
            return new ResponseDto("no data present with token ", null);
        }

    }

    @Override
    public ResponseDto updateBytoken(String token, int cart_Id, int quantity) {
        int id=jwtToken.decodeToken(token);
        System.out.println(id+"  id");
        int cartid=cartRepository.findIdByUserId(id);
        System.out.println(cartid+"  cart id");
        Optional<CartModel> data=cartRepository.findById(cartid);
        if((data!=null)&&cartid==cart_Id){
            data.get().setQuantity(quantity);
            return new ResponseDto("the qunatity Updated ",cartRepository.save(data.get()));
        }
        return new ResponseDto("the qunatity Updated ",null);
    }
    @Override
    public List<CartModel> getAlldata() {
        return cartRepository.findAll();
    }
}