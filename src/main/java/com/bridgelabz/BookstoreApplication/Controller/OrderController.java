package com.bridgelabz.BookstoreApplication.Controller;

import com.bridgelabz.BookstoreApplication.Dto.OrderDto;
import com.bridgelabz.BookstoreApplication.Dto.ResponseDto;
import com.bridgelabz.BookstoreApplication.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseDto addOrder(@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);
    }
    @GetMapping("/getOrder/{order_id}")
    public ResponseDto getById(@PathVariable int order_id){
        return orderService.getById(order_id);
    }
    @GetMapping("/getAllOrder")
    public ResponseDto getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/getByToken/{token}")
    public ResponseDto getByToken(@PathVariable String token){
        return orderService.getByToken(token);
    }
    @PutMapping("/cancelOrder")
    public String cancelOrder(@RequestParam String token , @RequestParam  int order_id){
        return orderService.cancelOrder(token,order_id);
    }
}
