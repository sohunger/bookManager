package com.huang.manager.service;

import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BookQr;
import com.huang.manager.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    public List<BookQr> userSelectBook(String selectInfo);

    boolean adminAddBook(@Param("bookName") String bookName,
                         @Param("bookAuthor") String bookAuthor,
                         @Param("bookPublish") String bookPublish,
                         @Param("bookCategory") Integer bookCategory,
                         @Param("bookPrice") Double bookPrice,
                         @Param("bookIntroduction") String bookIntroduction);

    boolean adminDeleteBook(int bookId);

    List<Book> adminFindBook(int bookCategory);

}
