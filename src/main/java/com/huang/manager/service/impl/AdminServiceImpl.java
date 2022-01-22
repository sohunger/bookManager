package com.huang.manager.service.impl;

import com.huang.manager.mapper.AdminMapper;
import com.huang.manager.pojo.Admin;
import com.huang.manager.pojo.BookCategory;
import com.huang.manager.pojo.User;
import com.huang.manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin adminLogin(String adminName, String adminPwd) {
        List<Admin> admins = adminMapper.selectAdmin(adminName);
        if(admins == null){
            return null;
        }
        for (Admin admin : admins) {
            boolean b = admin.getAdminPwd().equals(adminPwd);
            if(b == true){
                return admin;
            }
        }
        return null;
    }

    @Override
    public boolean addBookCategory(String categoryName) {
        return adminMapper.addBookCategory(categoryName);
    }

    @Override
    public List<User> adminSelectUser(){
        return adminMapper.adminSelectUser();
    }
}
