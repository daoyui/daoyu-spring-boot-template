package cn.idaoyu.project.action.user.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 一条秋刀鱼zz
 * @className RefreshTokenParams
 * @description 刷新令牌接口请求数据
 * @date 2022/12/24 2:15
 */
@Data
public class RefreshTokenParams {

    @NotEmpty(message = "刷新令牌不能为空")
    private String token;

}
