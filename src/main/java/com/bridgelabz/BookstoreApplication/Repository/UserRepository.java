package com.bridgelabz.BookstoreApplication.Repository;

import com.bridgelabz.BookstoreApplication.Model.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserData,Integer>{
    @Query(value = "select email from user_data where email= :email", nativeQuery = true)
    String findEmail(String email);

//    @Query(value = "select id from user_data where email= :email", nativeQuery = true)
//    int findIdByEmail(String email);

    @Query(value = "select id from user_data where email= :email", nativeQuery = true)
    int findByEmail(String email);

    @Query(value = "select otp from user_data where email= :email", nativeQuery = true)
    long findOtpByEmail(String email);
    @Query(value = "select password from user_data where email= :email", nativeQuery = true)
    String getPassword(String email);
}
