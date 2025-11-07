package com.zhiwu.virtualteamprojectmanagementsystem.controller.user;

import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.LoginDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.UserDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.ResultCodeEnum;
import com.zhiwu.virtualteamprojectmanagementsystem.service.UserService;
import com.zhiwu.virtualteamprojectmanagementsystem.utils.SessionConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

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
    public Result<Void> register (@RequestBody LoginDTO loginDTO) {
        return userService.register(loginDTO);
    }

    @Operation(summary = "登录")
    @PostMapping ("/login")
    public Result<UserDTO> login (@RequestBody LoginDTO loginDTO, HttpSession session) {
        Result<UserDTO> result = userService.login(loginDTO);
        if (result != null && ResultCodeEnum.SUCCESS.getCode().equals(result.getCode()) && result.getData() != null) {
            session.setAttribute(SessionConstants.LOGIN_USER, result.getData());
        }
        return result;
    }

}
