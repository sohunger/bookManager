package com.huang.manager;

import com.huang.manager.mapper.AdminMapper;
import com.huang.manager.mapper.BookMapper;
import com.huang.manager.mapper.BorrowingMapper;
import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BookQr;
import com.huang.manager.pojo.BorrowingBooks;
import com.huang.manager.pojo.User;
import com.huang.manager.service.BookService;
import com.huang.manager.service.impl.BookServiceImpl;
import com.huang.manager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.util.List;

@SpringBootTest
class ManagerApplicationTests {
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BorrowingMapper borrowingMapper;
    @Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads() {
        boolean haha = adminMapper.addBookCategory("haha");
        System.out.println(haha);
    }

}
