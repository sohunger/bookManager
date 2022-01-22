package com.huang.manager;

import com.huang.manager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);

    }

}
