package com.huang.manager.controller;

import com.huang.manager.pojo.Admin;
import com.huang.manager.pojo.User;
import com.huang.manager.service.AdminService;
import com.huang.manager.service.BorrowingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    BorrowingService borrowingService;
    @PostMapping("/adminLoginPage")
    public String adminLogin(@Param("adminName") String adminName,
                             @Param("adminPwd") String adminPwd,
                             HttpServletRequest request,
                             Model model){
        Admin admin = adminService.adminLogin(adminName, adminPwd);
        if(admin == null){
            model.addAttribute("msg","用户名或密码错误");
            return "adminLogin";
        }
        request.getSession().setAttribute("admin",admin);
        return "admin/index";
    }

    @RequestMapping("addCategoryPage")
    public String addBookCategoryPage(@Param("categoryName") String categoryName){
        boolean b = adminService.addBookCategory(categoryName);
        if(b == false){
            return "admin/addCategory";
        }
        return "admin/index";
    }

    //管理员查询用户
    @RequestMapping("amdinSelectUserPage")
    public String amdinSelectUserPage(Model model){
        List<User> users = adminService.adminSelectUser();
        model.addAttribute("users",users);
        return "admin/userManage";
    }

    @RequestMapping("adminSelectRecordPage")
    public String adminSelectRecordPage(Model model){
        model.addAttribute("records",borrowingService.adminSelectRecord());
        return "admin/userRecord";

    }
}
