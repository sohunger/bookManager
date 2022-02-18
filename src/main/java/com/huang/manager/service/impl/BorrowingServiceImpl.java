package com.huang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.manager.mapper.BookMapper;
import com.huang.manager.mapper.BorrowingMapper;
import com.huang.manager.mapper.UserMapper;
import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BorrowingBookRecord;
import com.huang.manager.pojo.BorrowingBooks;
import com.huang.manager.pojo.vo.UserBorrowRecord;
import com.huang.manager.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public PageInfo<BorrowingBookRecord> selectAllRecord(int pageNum, int pageSize,int userId) {

        List<BorrowingBookRecord> records = new LinkedList<>();
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowingBooks> list = borrowingMapper.selectAllRecord(userId); //查询借阅记录
        PageInfo<BorrowingBooks> pageInfo1 = new PageInfo<>(list);

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
        PageInfo<BorrowingBookRecord> pageInfo = new PageInfo<>(records);
        pageInfo.setTotal(pageInfo1.getTotal());
        return pageInfo;
    }

    @Override
    public PageInfo<UserBorrowRecord> adminSelectRecord(int pageNum, int pageSize) {
        //全部记录
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowingBooks> borrowingBooks = borrowingMapper.adminSelectRecord();
        PageInfo<BorrowingBooks> pageInfo = new PageInfo<>(borrowingBooks);
        List<UserBorrowRecord> records = new LinkedList<>();

        for (BorrowingBooks borrowingBook : borrowingBooks) {
            //转换时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(borrowingBook.getDate());
            gregorianCalendar.add(gregorianCalendar.DATE,30);
            Date date2 = gregorianCalendar.getTime();

//            Map<String,String> userRecord = new HashMap<>();
//            userRecord.put("userName",userMapper.selectById(borrowingBook.getUserId()).getUserName());
//            userRecord.put("bookName",bookMapper.selectById(borrowingBook.getBookId()).getBookName());
//            userRecord.put("borrowTime",simpleDateFormat.format(borrowingBook.getDate()));
//            userRecord.put("returnTime",simpleDateFormat.format(date2));
            String userName = userMapper.selectById(borrowingBook.getUserId()).getUserName();
            String bookName = bookMapper.selectById(borrowingBook.getBookId()).getBookName();
            String borrowTime = simpleDateFormat.format(borrowingBook.getDate());
            String returnTime = simpleDateFormat.format(date2);
            UserBorrowRecord record = new UserBorrowRecord(userName, bookName, borrowTime, returnTime);
            records.add(record);
        }
        PageInfo<UserBorrowRecord> pageInfo1 = new PageInfo<>(records);
        pageInfo1.setTotal(pageInfo.getTotal());
        return pageInfo1;
    }
}
