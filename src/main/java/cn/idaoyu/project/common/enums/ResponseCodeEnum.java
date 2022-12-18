package cn.idaoyu.project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 一条秋刀鱼zz
 * @className ResponseCodeEnum
 * @description 响应状态代码枚举
 * @date 2022/12/12 21:02
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 通用失败
     */
    FAIL(500);


    private final Integer value;

}
