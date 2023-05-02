package com.bridgelabz.BookstoreApplication.Repository;

import com.bridgelabz.BookstoreApplication.Model.CartModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends org.springframework.data.jpa.repository.JpaRepository<CartModel, Integer> {
//    @Query(value = "select * from cartModel where cart_id = :cartId",nativeQuery = true)
//    Object findDataById(int cartId);
    @Query(value = "select cart_id from cart_model where id = :userId",nativeQuery = true)
    int findIdByUserId(int userId);
    @Query(value = "select * from cart_model where id = :userId",nativeQuery = true)
    List<CartModel> findAllData(int userId);
    @Query(value = "select * from cart_model where id = :userId and book_id = :bookId",nativeQuery = true)
    CartModel findBookId(int bookId, int userId);
    @Query(value = "select * from cart_model where book_id = :bookId",nativeQuery = true)
    Optional<CartModel> findDataByBookId(int bookId);
}