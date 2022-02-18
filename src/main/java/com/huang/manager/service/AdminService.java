package com.huang.manager.service;

import com.github.pagehelper.PageInfo;
import com.huang.manager.pojo.Admin;
import com.huang.manager.pojo.BookCategory;
import com.huang.manager.pojo.User;

import java.util.List;

public interface AdminService {

    Admin adminLogin(String adminName, String adminPwd);

    //添加书籍种类
    boolean addBookCategory(String categoryName);

    //管理员查询用户
    PageInfo<User> adminSelectUser(int pageNum, int pageSize);

}
