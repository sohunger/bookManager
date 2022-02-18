package com.huang.manager.mapper;

import com.huang.manager.pojo.BorrowingBooks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BorrowingMapper {
    //学生借书（每一本书都只有一本）
    BorrowingBooks selectBookBorrowing(int bookId);
    //插入记录
    boolean insert(BorrowingBooks borrowingBooks);
    //学生借书记录
    List<BorrowingBooks> selectAllRecord(Integer userId);

    //还书
    boolean returnBook(int bookId);

    //管理员查看借书记录
    List<BorrowingBooks> adminSelectRecord();

}
