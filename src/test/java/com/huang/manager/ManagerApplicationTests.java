package com.huang.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.manager.mapper.AdminMapper;
import com.huang.manager.mapper.BookMapper;
import com.huang.manager.mapper.BorrowingMapper;
import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.*;
import com.huang.manager.pojo.vo.UserBorrowRecord;
import com.huang.manager.service.BorrowingService;
import com.huang.manager.service.impl.BookServiceImpl;
import com.huang.manager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    BorrowingService borrowingService;
    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        boolean haha = adminMapper.addBookCategory("haha");
        System.out.println(haha);
    }

    @Test
    public void Test02(){
        UserBorrowRecordVo vo = new UserBorrowRecordVo(1, 1, 10);
        System.out.println(borrowingMapper.userBorrowingNum(vo.getUserId()));
    }

    @Test
    public void Test03(){
        PageInfo pageInfo = borrowingService.selectAllRecord(1, 5, 1);
        System.out.println(pageInfo);

    }
    @Test
    public void Test04(){
        PageHelper.startPage(1,5);
        List<User> users = adminMapper.adminSelectUser();
        System.out.println("原始数据==============");
        System.out.println(users);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println("pageInfo总数数据================");
        System.out.println(pageInfo.getTotal());
        System.out.println("pageInfolist数据================");
        System.out.println(pageInfo.getList());
        System.out.println("pageInfo数据==============");
        System.out.println(pageInfo);
    }
    @Test
    public void Test05(){
        List<BorrowingBookRecord> records = new LinkedList<>();
       PageHelper.startPage(1,5);
        List<BorrowingBooks> borrowingBooks = borrowingMapper.selectAllRecord(1);
        PageInfo<BorrowingBooks> pageInfo1 = new PageInfo<>(borrowingBooks);
        System.out.println("最开始==============");
        System.out.println(pageInfo1.getTotal());
        System.out.println(borrowingBooks);
        System.out.println(borrowingBooks.size());
        for (BorrowingBooks borrowbook : borrowingBooks) {
            Book book = bookMapper.selectById(borrowbook.getBookId());
            BorrowingBookRecord record = new BorrowingBookRecord();
            record.setBookId(borrowbook.getBookId());
            record.setBookName(book.getBookName());
            //借书时间
            Date date1 = borrowbook.getDate();
            //时间增加工具
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date1);
            calendar.add(calendar.DATE,30); //把日期往后增加一天,整数  往后推,负数往前移动
            Date date2=calendar.getTime(); //这个时间就是日期往后推一天的结果
            //时间转字符串工具
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd ");
            record.setEarliestReturnDate(formatter.format(date1));
            record.setLastestReturnDate(formatter.format(date2));
            records.add(record);
        }
        System.out.println("原始数据++++++++");
        System.out.println(records);
        System.out.println(records.size());
        System.out.println("pageinfo++++++++++");
        PageInfo<BorrowingBookRecord> pageInfo = new PageInfo<>(records);
        System.out.println(pageInfo);
        pageInfo.setTotal(pageInfo1.getTotal());
        System.out.println(pageInfo.getTotal());
        pageInfo.setList(records);
        System.out.println("list++++++++++++");
        System.out.println(pageInfo.getList());
        System.out.println(pageInfo.getList().size());
    }

    @Test
    public void Test06(){
        PageInfo<UserBorrowRecord> mapPageInfo = borrowingService.adminSelectRecord(1, 5);
        System.out.println(mapPageInfo);
        System.out.println(mapPageInfo.getList());

    }
}
