package cn.idaoyu.project.action.user.data;

import lombok.Builder;
import lombok.Data;

/**
 * @author 一条秋刀鱼zz
 * @className RegisterResponse
 * @description 注册返回数据
 * @date 2022/12/24 0:15
 */
@Data
@Builder
public class RegisterResponse {

    /**
     * 提示信息
     */
    private String message;

}
