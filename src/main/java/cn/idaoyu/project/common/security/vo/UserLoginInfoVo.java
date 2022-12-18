package cn.idaoyu.project.common.security.vo;

import lombok.Data;

/**
 * @author 一条秋刀鱼zz
 * @className UserLoginInfoVo
 * @description 登录用户信息
 * @date 2022/12/12 20:29
 */
@Data
public class UserLoginInfoVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

}
