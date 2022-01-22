package com.huang.manager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingBooks {
    private Integer id; //id

    private Integer userId; //学生id

    private Integer bookId; //书本id

    private Date date;//时间
}