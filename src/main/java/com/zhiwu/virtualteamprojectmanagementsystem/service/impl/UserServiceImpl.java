package com.zhiwu.virtualteamprojectmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.LoginDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.UserDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.User;
import com.zhiwu.virtualteamprojectmanagementsystem.model.enums.UserRoleEnum;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.ResultCodeEnum;
import com.zhiwu.virtualteamprojectmanagementsystem.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiwu.virtualteamprojectmanagementsystem.model.vo.UserVO;
import com.zhiwu.virtualteamprojectmanagementsystem.service.UserService;
import com.zhiwu.virtualteamprojectmanagementsystem.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result<Void> register(LoginDTO loginDTO) {

        // 用户已存在，报错
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, loginDTO.getUsername());
        if (baseMapper.exists(wrapper)) {
            return Result.fail(ResultCodeEnum.USER_EXIST.getCode(), ResultCodeEnum.USER_EXIST.getMessage());
        }

        // 密码md5加密
        String md5Password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());

        // 用户不存在，创建并保存
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(md5Password);
        // 注册时默认设置为普通成员
        user.setRole(UserRoleEnum.MEMBER.getCode());
        baseMapper.insert(user);

        return Result.ok();
    }

    @Override
    public Result<UserDTO> login(LoginDTO loginDTO) {
        // 用户名不存在，报错
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, loginDTO.getUsername());
        User user = baseMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.fail(ResultCodeEnum.USER_NOT_EXIST.getCode(), ResultCodeEnum.USER_NOT_EXIST.getMessage());
        }

        // 密码是否正确
        String dbPassword = user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());

        if (!md5Password.equals(dbPassword)) {
            return Result.fail(ResultCodeEnum.PASSWORD_ERROR.getCode(), ResultCodeEnum.PASSWORD_ERROR.getMessage());
        }

        // 保存到threadlocal
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        UserHolder.saveUser(userDTO);

        return Result.ok(userDTO);
    }

    @Override
    public List<UserVO> listMember() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getRole, UserRoleEnum.MEMBER.getCode());
        List<User> userList = baseMapper.selectList(queryWrapper);
        return userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
    }
}
