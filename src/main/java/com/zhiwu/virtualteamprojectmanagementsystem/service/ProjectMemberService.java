package com.zhiwu.virtualteamprojectmanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.MoveProjectMemberDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.ProjectMemberDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.ProjectMember;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
public interface ProjectMemberService extends IService<ProjectMember> {

    void addProjectMember(ProjectMemberDTO addProjectMemberDTO);
    
    void updateProjectMember(MoveProjectMemberDTO moveProjectMemberDTO);

    void removeProjectMember(Long userId, Long projectId);
}
