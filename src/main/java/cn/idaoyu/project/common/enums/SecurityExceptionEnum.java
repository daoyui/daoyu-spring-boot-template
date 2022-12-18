package cn.idaoyu.project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 一条秋刀鱼zz
 * @className SecurityExceptionEnum
 * @description Spring Security 状态码枚举类
 * @date 2022/12/12 21:18
 */
@Getter
@AllArgsConstructor
public enum SecurityExceptionEnum {

    /**
     * 用户名或密码错误
     */
    USERNAME_PASSWORD_ERROR(10000, "用户名或密码错误"),
    LOGIN_REQUIRED(10001, "需要登录"),
    NO_ACCESS(10002, "无权限访问");

    /**
     * 错误代码
     */
    private final Integer errorCode;

    /**
     * 描述信息
     */
    private final String describe;

}
