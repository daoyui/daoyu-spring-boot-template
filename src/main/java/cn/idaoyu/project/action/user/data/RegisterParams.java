package cn.idaoyu.project.action.user.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 一条秋刀鱼zz
 * @className RegisterParams
 * @description 注册请求数据
 * @date 2022/12/24 0:10
 */
@Data
public class RegisterParams {
    // todo 缺少长度、复杂度校验

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

}
