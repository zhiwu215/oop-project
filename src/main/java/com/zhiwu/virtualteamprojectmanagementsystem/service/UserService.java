package com.zhiwu.virtualteamprojectmanagementsystem.service;

import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.LoginDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.UserDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.User;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
public interface UserService extends IService<User> {

    Result<Void> register(LoginDTO loginDTO);

    Result<UserDTO> login(LoginDTO loginDTO);

}
