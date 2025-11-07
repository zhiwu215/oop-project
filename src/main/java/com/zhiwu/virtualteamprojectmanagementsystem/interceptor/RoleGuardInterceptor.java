package com.zhiwu.virtualteamprojectmanagementsystem.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.UserDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.enums.UserRoleEnum;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.ResultCodeEnum;
import com.zhiwu.virtualteamprojectmanagementsystem.utils.SessionConstants;
import com.zhiwu.virtualteamprojectmanagementsystem.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class RoleGuardInterceptor implements HandlerInterceptor {

    /**
     * 项目经理接口路径前缀
     * 如果需要修改接口前缀（如改为 /admin），只需修改此常量
     */
    private static final String API_PREFIX = "/admin";
    
    /**
     * 普通成员接口路径前缀（可选，如果未来需要普通成员专用接口）
     * 如果需要启用普通成员接口验证，取消注释并修改路径前缀
     */
    // private static final String MEMBER_API_PREFIX = "/member";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        UserDTO user = session == null ? null : (UserDTO) session.getAttribute(SessionConstants.LOGIN_USER);
        if (user != null) {
            UserHolder.saveUser(user);
        }

        String requestURI = request.getRequestURI();
        
        // 项目经理接口验证：必须以 API_PREFIX 开头，仅项目经理可访问
        if (requestURI.startsWith(API_PREFIX)) {
            // 未登录的情况
            if (user == null) {
                writeJsonResponse(response, HttpStatus.UNAUTHORIZED.value(), 
                    Result.fail(ResultCodeEnum.ADMIN_LOGIN_AUTH.getCode(), 
                        ResultCodeEnum.ADMIN_LOGIN_AUTH.getMessage()));
                return false;
            }

            // 无权限访问的情况
            if (!UserRoleEnum.MANAGER.match(user.getRole())) {
                writeJsonResponse(response, HttpStatus.FORBIDDEN.value(), 
                    Result.fail(ResultCodeEnum.ADMIN_ACCESS_FORBIDDEN.getCode(), 
                        ResultCodeEnum.ADMIN_ACCESS_FORBIDDEN.getMessage()));
                return false;
            }
        }

        // 普通成员接口验证（可选）
        // 如果需要普通成员专用接口，取消以下注释并配置 MEMBER_API_PREFIX
        /*
        if (requestURI.startsWith(MEMBER_API_PREFIX)) {
            if (user == null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
            if (!UserRoleEnum.MEMBER.match(user.getRole())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }
        */

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserHolder.removeUser();
    }

    /**
     * 如果没有这个方法：
     * 没有这个方法（之前）
     * 状态码：401/403
     * Content-Type：未设置
     * 响应体：空
     * 浏览器行为：可能下载文件或显示空白
     * 
     * 有这个方法：
     * 状态码：401/403
     * Content-Type：application/json;charset=UTF-8
     * 响应体：标准 JSON 格式
     * 浏览器行为：正常显示 JSON 错误信息
     */
    // 写入 JSON 格式的错误响应
    private void writeJsonResponse(HttpServletResponse response, int status, Result<?> result) {
        try {

          // 设置 HTTP 状态码
            response.setStatus(status);
            
            // 设置 正确响应头
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            
            // 写入JSON格式的响应体
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            // 如果写入失败，至少设置状态码
            response.setStatus(status);
        }
    }
}

