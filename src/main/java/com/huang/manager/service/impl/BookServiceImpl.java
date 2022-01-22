package com.huang.manager.service.impl;

import com.huang.manager.mapper.BookMapper;
import com.huang.manager.mapper.BorrowingMapper;
import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BookQr;
import com.huang.manager.pojo.BorrowingBooks;
import com.huang.manager.pojo.User;
import com.huang.manager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowingMapper borrowingMapper;
    @Override
    public List<BookQr> userSelectBook(String selectInfo) {
        List<Book> books = bookMapper.userSelectBook("%"+selectInfo+"%");
        List<BookQr> bookQrs= new LinkedList<>();

        if(books == null){
            return bookQrs;
        }

        for (Book book : books) {
            BookQr bookQr = new BookQr();
            bookQr.setBookId(book.getBookId());
            bookQr.setBookName(book.getBookName());
            bookQr.setBookAuthor(book.getBookAuthor());
            bookQr.setBookPublish(book.getBookPublish());
            BorrowingBooks borrowingBooks = borrowingMapper.selectBookBorrowing(book.getBookId());
            if(borrowingBooks == null){
                bookQr.setIsExist("可借");
            }else {
                bookQr.setIsExist("不可借");
            }
            bookQrs.add(bookQr);
        }
        return bookQrs;
    }

    @Override
    public boolean adminAddBook(String bookName,
                                String bookAuthor,
                                String bookPublish,
                                Integer bookCategory,
                                Double bookPrice,
                                String bookIntroduction) {
        return bookMapper.addBook(bookName, bookAuthor, bookPublish, bookCategory, bookPrice, bookIntroduction);
    }

    @Override
    public boolean adminDeleteBook(int bookId) {
        return bookMapper.deleteBook(bookId);
    }

    @Override
    public List<Book> adminFindBook(int bookCategory) {
        return bookMapper.adminSelectBook(bookCategory);
    }

}
