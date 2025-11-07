package com.zhiwu.virtualteamprojectmanagementsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MoveProjectMemberDTO {
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "关联的用户表id")
    private Long userId;

    @Schema(description = "关联项目表id")
    private Long projectId;
}
