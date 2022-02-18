package com.huang.manager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户实体类
public class User {
    private Integer userId;

    private String userName;

    private String userPwd;

    private String userEmail;

}