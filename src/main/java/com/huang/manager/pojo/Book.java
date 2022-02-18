package com.huang.manager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//书的实体类
public class Book {
    private Integer bookId; //书编号

    private String bookName;//书名

    private String bookAuthor;//书作者

    private String bookPublish;//书出版社

    private Integer bookCategory;//书种类

    private Double bookPrice;//书价格

    private String bookIntroduction;//书简介

}