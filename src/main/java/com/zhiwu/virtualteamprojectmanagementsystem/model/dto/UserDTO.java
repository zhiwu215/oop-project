package com.zhiwu.virtualteamprojectmanagementsystem.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private Integer role;
}