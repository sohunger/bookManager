package com.huang.manager.service.impl;

import com.huang.manager.mapper.BorrowingMapper;
import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.BorrowingBooks;
import com.huang.manager.pojo.User;
import com.huang.manager.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BorrowingMapper borrowingMapper;
    //学生登录
    @Override
    public User userLogin(String username, String password) {
        List<User> users = userMapper.selectUser(username);
        for (User user : users) {
            if(user.getUserPwd().equals(password) ){
                return user;
            }
        }
        return null;
    }

    //学生借书
    @Override
    public boolean userBorrowingBook(int bookId, HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        BorrowingBooks borrowingBooks = borrowingMapper.selectBookBorrowing(bookId);
        if (borrowingBooks != null){
            return false;
        }
        BorrowingBooks books = new BorrowingBooks();
        books.setBookId(bookId);
        books.setUserId(user.getUserId());
        books.setDate(new Date());

        //插入记录
        borrowingMapper.insert(books);
        return true;
    }

    @Override
    public boolean userReturnBook(int bookId) {
        boolean b = borrowingMapper.returnBook(bookId);
        return b;
    }

    //学生修改信息
    @Override
    public int userModifyInfo(User user,HttpServletRequest request) {
        User user1 = (User)request.getSession().getAttribute("user");
        if(user1.getUserName().equals(user.getUserName())){
            if(user1.getUserPwd().equals(user.getUserPwd())){
                if(user1.getUserEmail().equals(user.getUserEmail())){
                    return 0;//表示用户修改后的信息没有变
                }
            }
        }
        boolean b = userMapper.updateUser(user.getUserName(), user.getUserPwd(), user.getUserEmail(),user.getUserId());
        if(b == true){
            return 1;//表示修改成功
        }
        return 2;//表示修改失败
    }

    @Override
    public User getUser(int userId) {
        return userMapper.selectById(userId);
    }
}
