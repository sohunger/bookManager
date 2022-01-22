package com.huang.manager.controller;

import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BookQr;
import com.huang.manager.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/bookFindMessage")
    public String selectBookMessage(Model model,
                                    @Param("selectInfo") String selectInfo){
        List<BookQr> bookQrs = bookService.userSelectBook(selectInfo);
        if(bookQrs == null){
            model.addAttribute("msg","没有此书");
        }else{
            model.addAttribute("bookQrs",bookQrs);
        }
        return "/user/findBook";
    }

    @RequestMapping("/adminAddBookPage")
    public String adminAddBookPage(@Param("bookName") String bookName,
                               @Param("bookAuthor") String bookAuthor,
                               @Param("bookPublish") String bookPublish,
                               @Param("bookCategory") Integer bookCategory,
                               @Param("bookPrice") Double bookPrice,
                               @Param("bookIntroduction") String bookIntroduction){
        System.out.println(bookName);
        System.out.println(bookAuthor);
        System.out.println(bookPublish);
        System.out.println(bookCategory);
        System.out.println(bookPrice);
        System.out.println(bookIntroduction);
        bookService.adminAddBook(bookName,
                bookAuthor,
                bookPublish,
                bookCategory,
                bookPrice,
                bookIntroduction);
        return "admin/index";
    }

    @RequestMapping("/adminDeleteBookPage")
    public String adminDeleteBookPage(@Param("bookId") int bookId){
        bookService.adminDeleteBook(bookId);
        return "admin/index";
    }

    @RequestMapping("/adminFindBookPage")
    public String adminFindBookPage(int bookCategory,
                                    Model model){
        model.addAttribute("books",bookService.adminFindBook(bookCategory));
        return "admin/findBook";
    }

    @RequestMapping("/qq")
    public List<BookQr> selectQq(String selectInfo){
        List<BookQr> bookQrs = bookService.userSelectBook(selectInfo);
        return bookQrs;
    }

}
