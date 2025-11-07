package com.zhiwu.virtualteamprojectmanagementsystem.controller.admin;

import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.ProjectDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.UserDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.Project;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.service.ProjectService;
import com.zhiwu.virtualteamprojectmanagementsystem.utils.UserHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
@Tag(name = "项目管理")
@RestController
@RequestMapping("/admin/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "查询所有项目")
    @GetMapping("/list")
    public Result<List<Project>> listProject() {
        List<Project> projectList = projectService.list();
        return Result.ok(projectList);
    }

    @Operation(summary = "根据id创建或更新项目")
    @PostMapping("/addOrUpdate")
    public Result<Void> addOrUpdateProject(@RequestBody ProjectDTO projectDTO){
        UserDTO currentUser = UserHolder.getUser();
        log.info("当前用户为：{}", currentUser);

        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        project.setUserId(currentUser.getId());
        projectService.saveOrUpdate(project);
        return Result.ok();
    }

    @Operation(summary = "根据id删除项目")
    @DeleteMapping("/delete")
    public Result<Void> deleteProject(@RequestParam Long id){
        projectService.removeById(id);
        return Result.ok();
    }
}
