package com.bridgelabz.BookstoreApplication.Repository;

import com.bridgelabz.BookstoreApplication.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends org.springframework.data.jpa.repository.JpaRepository<OrderModel, Integer> {
    @Query(value = "SELECT * FROM bookstore.order_model where user_id = :userId ",nativeQuery = true)
    List<OrderModel> findByUserId(int userId);

}
