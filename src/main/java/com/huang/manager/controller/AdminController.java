package com.huang.manager.controller;

import com.github.pagehelper.PageInfo;
import com.huang.manager.pojo.Admin;
import com.huang.manager.pojo.User;
import com.huang.manager.pojo.vo.UserBorrowRecord;
import com.huang.manager.service.AdminService;
import com.huang.manager.service.BorrowingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//添加类别界面
    @RequestMapping("addCategoryPage")
    public String addBookCategoryPage(@Param("categoryName") String categoryName){
        boolean b = adminService.addBookCategory(categoryName);
        if(b == false){
            return "admin/addCategory";
        }
        return "admin/index";
    }

    //管理员查询用户
    @CrossOrigin(origins = "*")
    @GetMapping("amdinSelectUserPageInfo")
    @ResponseBody
    public Map amdinSelectUserPage(Integer page,
                                   Integer limit){
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<User> users = adminService.adminSelectUser(page,limit);
        map.put("code",0);
        map.put("msg",1);
        map.put("count",users.getTotal());
        map.put("data",users.getList());
        return map;
    }

//    管理员查看记录
    @RequestMapping("adminSelectRecordPageInfo")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Map adminSelectRecordPage(int page,
                                        int limit){
        System.out.println("page:"+page);
        System.out.println("limit:"+limit);
        PageInfo<UserBorrowRecord> PageInfo = borrowingService.adminSelectRecord(page, limit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg",1);
        map.put("count",PageInfo.getTotal());
        map.put("data",PageInfo.getList());
        return map;

    }
}
