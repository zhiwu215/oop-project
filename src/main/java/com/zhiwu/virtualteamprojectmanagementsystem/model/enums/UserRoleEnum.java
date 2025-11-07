package com.zhiwu.virtualteamprojectmanagementsystem.model.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    /**
     * 项目经理
     */
    MANAGER(1),
    /**
     * 普通成员
     */
    MEMBER(2);

    private final int code;

    UserRoleEnum(int code) {
        this.code = code;
    }

    public boolean match(Integer role) {
        return role != null && role == this.code;
    }
}

