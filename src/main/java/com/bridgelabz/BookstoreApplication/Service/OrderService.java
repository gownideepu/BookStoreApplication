package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.OrderDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;

public interface OrderService {

    ResponseDto addOrder(OrderDto orderDto);

    ResponseDto getById(int orderId);

    ResponseDto getAllOrders();

    ResponseDto getByToken(String token);

    String cancelOrder(String token, int orderId);
}
