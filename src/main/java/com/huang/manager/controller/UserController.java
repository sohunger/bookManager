package com.huang.manager.controller;

import com.huang.manager.pojo.BorrowingBookRecord;
import com.huang.manager.pojo.User;
import com.huang.manager.service.BorrowingService;
import com.huang.manager.service.UserService;
import com.huang.manager.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BorrowingService borrowingService;
    @PostMapping("/userLogin")
    public String userLogin(Model model,
                            @Param("username") String username,
                            @Param("password") String password,
                            HttpServletRequest servletRequest){
        User user = userService.userLogin(username, password);
        if(user == null){
            model.addAttribute("msg","您的账号或密码错误");
            return "login";
        }
        servletRequest.getSession().setAttribute("user",user);
        return "user/index";
    }

    @RequestMapping("/userBorrowRecordPage")
    public String borrowRecordPage(HttpServletRequest request,
                                   Model model){
        List<BorrowingBookRecord> records = borrowingService.selectAllRecord(request);
        model.addAttribute("records",records);
        return "user/borrowingBookRecord";
    }

    @RequestMapping("/returnBook")
    public String returnBook(Model model,
                             @Param("bookId") int bookId){
        System.out.println(bookId);
        userService.userReturnBook(bookId);
        return "redirect:userBorrowRecordPage";
    }

    @RequestMapping("/modifyInfoPage")
    public String modifyInfoPage(HttpServletRequest request,
                                 @Param("userName") String userName,
                                 @Param("userPwd") String userPwd,
                                 @Param("userEmail") String userEmail,
                                 Model model){
        User user = (User)request.getSession().getAttribute("user");

        int i = userService.userModifyInfo(new User(user.getUserId(), userName, userPwd, userEmail), request);
        System.out.println(i);
        if(i == 0){
            model.addAttribute("modifyRemind","您修改后的信息和以前一样，请重试");
            return "user/modifyInfo";
        }else if (i == 1){
            model.addAttribute("modifyRemind","修改成功");
            return "login";
        }else {
            model.addAttribute("modifyRemind","修改失败，请重试");
            return "user/modifyInfo";
        }
    }
}
