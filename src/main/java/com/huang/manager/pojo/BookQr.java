package com.huang.manager.pojo;

import lombok.Data;

//书籍查询结果
@Data
//用户查询书籍时返回的信息实体类
public class BookQr {
    private Integer bookId;  //书籍id

    private String bookName; //书名

    private String bookAuthor;//作者

    private String bookPublish;//出版社

    private String isExist;  //是否可借
}
