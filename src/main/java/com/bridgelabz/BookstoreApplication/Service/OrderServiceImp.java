package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.OrderDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Model.BookModel;
import com.bridgelabz.BookstoreApplication.Model.CartModel;
import com.bridgelabz.BookstoreApplication.Model.OrderModel;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Repository.CartRepository;
import com.bridgelabz.BookstoreApplication.Repository.OrderRepository;
import com.bridgelabz.BookstoreApplication.Repository.UserRepository;
import com.bridgelabz.BookstoreApplication.Util.EmailService;
import com.bridgelabz.BookstoreApplication.Util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookService bookService;

    @Override
    public ResponseDto addOrder(OrderDto orderDto) {
        int user_id = jwtToken.decodeToken(orderDto.getToken());
        List<CartModel> cartList = cartRepository.findAllData(user_id);
        UserData userData = userRepository.findById(user_id).orElseThrow();
        List<OrderModel> ordersPlaced = new ArrayList<>();
        System.out.println("..........");
        System.out.println("cart list  " + cartList);
        for (int i = 0; i < cartList.size(); i++) {
            int bookId = cartList.get(i).getBookModel().getBook_id();
            BookModel book = bookService.getById(bookId);
            float price = cartList.get(i).getBookModel().getBookPrice();
            int quantity = cartList.get(i).getQuantity();
            float totalPrice = price * quantity;
            LocalDate date = LocalDate.now();
            OrderModel placeOrder = new OrderModel(user_id, quantity, book, price, orderDto.getAddress(), totalPrice, date);
            ordersPlaced.add(placeOrder);
            orderRepository.save(placeOrder);
            cartRepository.deleteById(cartList.get(i).getCart_id());
        }
        emailService.sendEmail(userData.getEmail(), "The order is Successfully placed " + userData.getFirstName(), "The order Details are given " + ordersPlaced);
        return new ResponseDto("The order added for the user ", ordersPlaced);
    }

    @Override
    public ResponseDto getById(int orderId) {
        return new ResponseDto("The Data for the order id ", orderRepository.findById(orderId));
    }

    @Override
    public ResponseDto getAllOrders() {
        return new ResponseDto("The oder Data", orderRepository.findAll());
    }

    @Override
    public ResponseDto getByToken(String token) {
        int userId = jwtToken.decodeToken(token);
        List<OrderModel> userOrders = orderRepository.findByUserId(userId);
        return new ResponseDto("the orders related to user", userOrders);
    }

    @Override
    public String cancelOrder(String token, int orderId) {
        int userId=jwtToken.decodeToken(token);
        Optional<OrderModel> orderData=orderRepository.findById(orderId);
        System.out.println(".....\n"+orderData);
        if(orderData!=null) {
            if (orderData.get().getUser_id() == userId) {
                orderData.get().setCancel(true);
                orderRepository.save(orderData.get());
                return "Your order has been canceled";
            } else {
                return "The order not canceled check the userid ";
            }
        }return "The Order id is not present";
    }
}