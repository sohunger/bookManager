package com.huang.manager.service;

import com.huang.manager.pojo.BorrowingBookRecord;
import com.huang.manager.pojo.BorrowingBooks;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BorrowingService {
        //用户查询借书记录
    List<BorrowingBookRecord> selectAllRecord(HttpServletRequest request);

    //管理员查询借书记录
    List<Map> adminSelectRecord();
}
