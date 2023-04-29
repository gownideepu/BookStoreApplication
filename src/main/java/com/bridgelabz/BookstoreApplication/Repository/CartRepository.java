package com.bridgelabz.BookstoreApplication.Repository;

import com.bridgelabz.BookstoreApplication.Model.CartModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends org.springframework.data.jpa.repository.JpaRepository<CartModel, Integer> {
//    @Query(value = "select * from cartModel where cart_id = :cartId",nativeQuery = true)
//    Object findDataById(int cartId);
    @Query(value = "select cart_id from cart_model where id = :userId",nativeQuery = true)
    int findIdByUserId(int userId);
}