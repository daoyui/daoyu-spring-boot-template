package cn.idaoyu.project.action.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 一条秋刀鱼zz
 * @className UserRoleStatusEnum
 * @description 用户角色状态枚举
 * @date 2022/12/18 16:08
 */
@Getter
@AllArgsConstructor
public enum UserRoleStatusEnum {

    /**
     * 角色状态正常
     */
    IN_USE(1),
    /**
     * 角色被锁定
     */
    LOCKING(2);


    private final Integer value;

}
