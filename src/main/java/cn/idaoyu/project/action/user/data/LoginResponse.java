package cn.idaoyu.project.action.user.data;

import lombok.Builder;
import lombok.Data;

/**
 * @author 一条秋刀鱼zz
 * @className LoginResponse
 * @description 登录成功
 * @date 2022/12/12 21:09
 */
@Data
@Builder
public class LoginResponse {

    /**
     * 用于访问需要授权的接口的 token
     */
    private String accessToken;

    /**
     * 当 accessToken 过期时，使用该 token 刷新登录状态
     */
    private String refreshToken;

}
