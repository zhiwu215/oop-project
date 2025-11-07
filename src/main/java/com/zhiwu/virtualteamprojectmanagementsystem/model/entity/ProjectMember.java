package com.zhiwu.virtualteamprojectmanagementsystem.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zhiwu
 * @since 2025-11-07
 */
@Getter
@Setter
@ToString
@TableName("project_member")
public class ProjectMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的用户表id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 关联项目表id
     */
    @TableField("project_id")
    private Long projectId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
