package com.zhiwu.virtualteamprojectmanagementsystem.controller;

import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.LoginDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "注册")
    @PostMapping ("/register")
    public Result register (@RequestBody LoginDTO loginDTO) {
        return userService.register(loginDTO);
    }

    @Operation(summary = "登录")
    @PostMapping ("/login")
    public Result login (@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

}
