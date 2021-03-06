package com.huang.manager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户借书记录返回的实体类
public class BorrowingBookRecord {
    private int bookId;
    private String bookName;
    private String earliestReturnDate;
    private String lastestReturnDate;
}
