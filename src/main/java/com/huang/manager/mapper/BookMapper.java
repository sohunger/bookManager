package com.huang.manager.mapper;

import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.BookQr;
import com.huang.manager.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {

    //用户查询书籍
    List<Book> userSelectBook(String selectInfo);
    //学生通过id查询书籍
    Book selectById(int bookId);



    //管理员增加
    boolean addBook(String bookName,
                    String bookAuthor,
                    String bookPublish,
                    Integer bookCategory,
                    Double bookPrice,
                    String bookIntroduction);

    //管理员删除书籍
    boolean deleteBook(int bookId);

    //管理员查找书籍
    List<Book> adminSelectBook(int bookCategory);

}
