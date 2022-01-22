package com.huang.manager.mapper;

import com.huang.manager.pojo.Book;
import com.huang.manager.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    //通过用户名查找用户
    List<User> selectUser(String userName);
    //通过id查找用户
    User selectById(int userId);

    //学生更新信息
    boolean updateUser(@Param("userName") String userName,
                       @Param("userPwd") String userPwd,
                       @Param("userEmail") String userEmail,
                       @Param("userId") int userId);


}
