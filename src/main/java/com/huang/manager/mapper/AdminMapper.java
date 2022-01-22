package com.huang.manager.mapper;

import com.huang.manager.pojo.Admin;
import com.huang.manager.pojo.BookCategory;
import com.huang.manager.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
@Mapper
public interface AdminMapper {

    //登录校验
    List<Admin> selectAdmin(String adminName);

    //增加书籍类别
    boolean addBookCategory( String categoryName);

    //管理员查询用户
    List<User> adminSelectUser();
}
