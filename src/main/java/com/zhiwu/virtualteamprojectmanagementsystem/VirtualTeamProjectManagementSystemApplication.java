package com.zhiwu.virtualteamprojectmanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhiwu.virtualteamprojectmanagementsystem.mapper")
public class VirtualTeamProjectManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualTeamProjectManagementSystemApplication.class, args);
    }

}
