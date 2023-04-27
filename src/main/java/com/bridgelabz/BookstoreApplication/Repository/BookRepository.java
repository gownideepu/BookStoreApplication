package com.bridgelabz.BookstoreApplication.Repository;

import com.bridgelabz.BookstoreApplication.Model.BookModel;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Integer> {
    @Query(value = "SELECT * FROM bookStore.book_model where book_name = :name", nativeQuery = true)
    List<BookModel> getBookByName(String name);
}
