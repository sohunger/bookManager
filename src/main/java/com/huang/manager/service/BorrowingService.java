package com.huang.manager.service;

import com.github.pagehelper.PageInfo;
import com.huang.manager.pojo.vo.UserBorrowRecord;

public interface BorrowingService {
        //用户查询借书记录
    PageInfo selectAllRecord(int pageNum, int pageSize,int userId);

    //管理员查询借书记录
    PageInfo<UserBorrowRecord> adminSelectRecord(int pageNum, int pageSize);

}
