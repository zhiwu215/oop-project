package com.zhiwu.virtualteamprojectmanagementsystem.service.impl;

import com.zhiwu.virtualteamprojectmanagementsystem.mapper.ProjectMapper;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.ProjectDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.Project;
import com.zhiwu.virtualteamprojectmanagementsystem.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}
