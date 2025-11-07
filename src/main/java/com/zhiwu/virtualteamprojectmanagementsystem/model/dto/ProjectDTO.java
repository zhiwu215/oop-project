package com.zhiwu.virtualteamprojectmanagementsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "项目DTO")
@Data
public class ProjectDTO {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "项目名称")
    private String name;

    @Schema(description = "项目描述")
    private String description;

    @Schema(description = "截止日期", example = "2006-02-15")
    private LocalDate endDate;
}
