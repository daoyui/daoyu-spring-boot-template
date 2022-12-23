package cn.idaoyu.project.action.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 一条秋刀鱼zz
 * @className TRole
 * @description 角色表
 * @date 2022/12/18 15:54
 */
@Schema
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_role")
public class TRole implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "")
    private Long id;

    /**
     * 角色名字
     */
    @TableField(value = "`name`")
    @Schema(description = "角色名字")
    private String name;

    /**
     * 角色状态
     */
    @TableField(value = "`status`")
    @Schema(description = "角色状态")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    @Schema(description = "创建时间")
    private Date createdTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @Schema(description = "修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
