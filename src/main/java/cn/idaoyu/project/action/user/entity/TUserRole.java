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

/**
 * @author 一条秋刀鱼zz
 * @className TUserRole
 * @description 用户、角色关联表
 * @date 2022/12/18 16:03
 */
@Schema
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user_role")
public class TUserRole implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 用户名（账号）
     */
    @TableField(value = "username")
    @Schema(description = "用户名（账号）")
    private String username;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @Schema(description = "角色id")
    private Long roleId;

    /**
     * 角色名字
     */
    @TableField(value = "role_name")
    @Schema(description = "角色名字")
    private String roleName;

    private static final long serialVersionUID = 1L;
}
