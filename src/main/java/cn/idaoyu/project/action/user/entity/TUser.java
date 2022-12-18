package cn.idaoyu.project.action.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author 一条秋刀鱼zz
 * @className TUser
 * @description 用户表实体类
 * @date 2022/12/18 15:36
 */
@ApiModel(value = "用户表")
@Data
@Builder
@TableName(value = "t_user")
public class TUser implements UserDetails, Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 用户名（账号）
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名（账号）")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 用户是否已经过期
     */
    @TableField(value = "is_account_non_expired")
    @ApiModelProperty(value = "用户是否已经过期")
    private Boolean isAccountNonExpired;

    /**
     * 指示用户是锁定还是解锁
     */
    @TableField(value = "is_account_non_locked")
    @ApiModelProperty(value = "指示用户是锁定还是解锁")
    private Boolean isAccountNonLocked;

    /**
     * 指示用户凭据（密码）是否已过期
     */
    @TableField(value = "is_credentials_non_expired")
    @ApiModelProperty(value = "指示用户凭据（密码）是否已过期")
    private Boolean isCredentialsNonExpired;

    /**
     * 指示用户是启用还是禁用
     */
    @TableField(value = "is_enabled")
    @ApiModelProperty(value = "指示用户是启用还是禁用")
    private Boolean isEnabled;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 授予用户的权限
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "授予用户的权限")
    private Collection<? extends GrantedAuthority> authorities;

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
