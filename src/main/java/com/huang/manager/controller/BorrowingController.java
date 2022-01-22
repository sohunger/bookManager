package com.huang.manager.controller;

import com.huang.manager.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BorrowingController {
    @Autowired
    UserService userService;
    @RequestMapping("/userBorrowing")
    public String userBorrowingBook(Model model,
                                    @Param("bookId") int bookId, HttpServletRequest httpServletRequest){
        boolean b = userService.userBorrowingBook(bookId, httpServletRequest);
        if(b == true){
            model.addAttribute("msg","借书成功");
        }else {
            model.addAttribute("msg","借书失败");
        }
        return "user/borrowingBook";
    }
}
