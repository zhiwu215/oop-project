package com.zhiwu.virtualteamprojectmanagementsystem.controller.admin;

import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.MoveProjectMemberDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.dto.ProjectMemberDTO;
import com.zhiwu.virtualteamprojectmanagementsystem.model.entity.ProjectMember;
import com.zhiwu.virtualteamprojectmanagementsystem.model.result.Result;
import com.zhiwu.virtualteamprojectmanagementsystem.service.ProjectMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "项目成员管理")
@RestController
@RequestMapping("/admin/projectMember")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @Operation(summary = "查询所有项目成员")
    @GetMapping("/list")
    public Result<List<ProjectMember>> listProjectMembers() {
        List<ProjectMember> projectMemberList = projectMemberService.list();
        return Result.ok(projectMemberList);
    }

    @Operation(summary = "添加项目成员")
    @PostMapping("/add")
    public Result<Void> addProjectMember(@RequestBody ProjectMemberDTO addProjectMemberDTO) {
        projectMemberService.addProjectMember(addProjectMemberDTO);
        return Result.ok();
    }

    @Operation(summary = "移除团队成员到特定项目")
    @PostMapping("/update")
    public Result<Void> updateProjectMember(@RequestBody MoveProjectMemberDTO moveProjectMemberDTO) {
        projectMemberService.updateProjectMember(moveProjectMemberDTO);
        return Result.ok();
    }

    @Operation(summary = "删除某项目的成员")
    @DeleteMapping("/delete")
    public Result<Void> deleteProjectMember(@RequestParam Long userId, @RequestParam Long projectId) {
        projectMemberService.removeProjectMember(userId, projectId);
        return Result.ok();
    }
}
