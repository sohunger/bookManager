package com.huang.manager.service;

import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    User userLogin(String username,String password);

    boolean userBorrowingBook(int bookId, HttpServletRequest httpServletRequest);

    //学生还书
    boolean userReturnBook(int bookId);

    //学生修改信息
    int userModifyInfo(User user,HttpServletRequest request);

    User getUser(int userId);


}
