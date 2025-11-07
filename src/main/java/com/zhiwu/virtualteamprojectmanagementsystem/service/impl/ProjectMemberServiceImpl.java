package com.zhiwu.virtualteamprojectmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiwu.virtualteamprojectmanagementsystem.mapper.ProjectMemberMapper;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.MoveProjectMemberDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.ProjectMemberDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.ProjectMember;
import com.zhiwu.virtualteamprojectmanagementsystem.service.ProjectMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
@Service
public class ProjectMemberServiceImpl extends ServiceImpl<ProjectMemberMapper, ProjectMember> implements ProjectMemberService {

    @Override
    public void addProjectMember(ProjectMemberDTO addProjectMemberDTO) {
        ProjectMember projectMember = new ProjectMember();
        BeanUtils.copyProperties(addProjectMemberDTO, projectMember);
        baseMapper.insert(projectMember);
    }

    @Override
    public void updateProjectMember(MoveProjectMemberDTO moveProjectMemberDTO) {
        ProjectMember projectMember = new ProjectMember();
        BeanUtils.copyProperties(moveProjectMemberDTO, projectMember);
        baseMapper.updateById(projectMember);
    }

    @Override
    public void removeProjectMember(Long userId, Long projectId) {
        LambdaQueryWrapper<ProjectMember> queryWrapper = new LambdaQueryWrapper<ProjectMember>().eq(ProjectMember::getUserId, userId)
                                                                                      .eq(ProjectMember::getProjectId, projectId);

        baseMapper.delete(queryWrapper);
    }
}
