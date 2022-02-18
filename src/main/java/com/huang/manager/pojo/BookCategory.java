package com.huang.manager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//书籍类别实体类
public class BookCategory {
    private Integer categoryId;

    private String categoryName;
}