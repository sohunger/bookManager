package com.huang.manager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//管理员的实体类
public class Admin {
    private Integer adminId;

    private String adminName;

    private String adminPwd;

    private String adminEmail;
}
