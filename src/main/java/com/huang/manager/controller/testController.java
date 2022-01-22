package com.huang.manager.controller;

import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BookQr;
import com.huang.manager.pojo.User;
import com.huang.manager.service.BookService;
import com.huang.manager.service.UserService;
import com.huang.manager.service.impl.BookServiceImpl;
import com.huang.manager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class testController {
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserMapper userMapper;






}
