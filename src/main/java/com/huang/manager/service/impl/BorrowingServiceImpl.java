package com.huang.manager.service.impl;

import com.huang.manager.mapper.BookMapper;
import com.huang.manager.mapper.BorrowingMapper;
import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BorrowingBookRecord;
import com.huang.manager.pojo.BorrowingBooks;
import com.huang.manager.pojo.User;
import com.huang.manager.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    @Autowired
    BorrowingMapper borrowingMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<BorrowingBookRecord> selectAllRecord(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return null;
        }
        List<BorrowingBookRecord> records = new LinkedList<>(); //待返回的借阅记录列表
        List<BorrowingBooks> list = borrowingMapper.selectAllRecord(user.getUserId()); //查询借阅记录

        if(list == null){
            return null;
        }
        for (BorrowingBooks borrowbook : list) {
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
        return records;
    }

    @Override
    public List<Map> adminSelectRecord() {
        //全部记录
        List<BorrowingBooks> borrowingBooks = borrowingMapper.adminSelectRecord();
        List<Map> recordMap = new LinkedList<>();

        for (BorrowingBooks borrowingBook : borrowingBooks) {
            //转换时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(borrowingBook.getDate());
            gregorianCalendar.add(gregorianCalendar.DATE,30);
            Date date2 = gregorianCalendar.getTime();

            Map<Integer,String> userRecord = new HashMap<>();
            userRecord.put(1,userMapper.selectById(borrowingBook.getUserId()).getUserName());
            userRecord.put(2,bookMapper.selectById(borrowingBook.getBookId()).getBookName());
            userRecord.put(3,simpleDateFormat.format(borrowingBook.getDate()));
            userRecord.put(4,simpleDateFormat.format(date2));
            recordMap.add(userRecord);
        }
        return recordMap;
    }
}
