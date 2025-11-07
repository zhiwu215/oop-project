package com.zhiwu.virtualteamprojectmanagementsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "项目成员DTO")
@Data
public class ProjectMemberDTO {

    @Schema(description = "关联的用户表id")
    private Long userId;

    @Schema(description = "关联项目表id")
    private Long projectId;
}
