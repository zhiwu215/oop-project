package com.zhiwu.virtualteamprojectmanagementsystem.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户VO")
@Data
public class UserVO {
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户名")
    private String username;
}
