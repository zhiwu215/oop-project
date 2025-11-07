package com.zhiwu.virtualteamprojectmanagementsystem.controller.admin;


import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.model.vo.UserVO;
import com.zhiwu.virtualteamprojectmanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "项目经理模块")
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @Operation(summary = "查询所有普通成员")
    @GetMapping("listMember")
    public Result<List<UserVO>> listMember() {
        List<UserVO> list = userService.listMember();
        return Result.ok(list);
    }
}
