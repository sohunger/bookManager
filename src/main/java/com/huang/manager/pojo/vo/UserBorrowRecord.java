package com.huang.manager.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//管理员查看用户借书记录的实体类
public class UserBorrowRecord {
    private String userName;
    private String bookName;
    private String borrowTime;
    private String returnTime;

}
