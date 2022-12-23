package cn.idaoyu.project.common.utils;

import cn.idaoyu.project.common.enums.ResponseCodeEnum;
import cn.idaoyu.project.common.vo.Result;

/**
 * @author 一条秋刀鱼zz
 * @className ResultUtil
 * @description 统一返回工具类
 * @date 2022/12/12 21:00
 */
public class ResultUtil {

    /**
     * 请求成功
     *
     * @return Result
     */
    public static Result isOk() {
        Result.ResultBuilder builder = Result.builder();
        builder.code(ResponseCodeEnum.SUCCESS.getValue());
        builder.msg("success");
        builder.timestamp(System.currentTimeMillis());
        return builder.build();
    }

    /**
     * 请求成功
     *
     * @param data 响应数据
     * @return Result
     */
    public static Result isOk(Object data) {
        Result.ResultBuilder builder = Result.builder();
        builder.code(ResponseCodeEnum.SUCCESS.getValue());
        builder.msg("success");
        builder.data(data);
        builder.timestamp(System.currentTimeMillis());
        return builder.build();
    }

    /**
     * 请求失败
     *
     * @param code         错误代码
     * @param errorMessage 错误信息
     * @return Result
     */
    public static Result error(Integer code, String errorMessage) {
        Result.ResultBuilder builder = Result.builder();
        builder.code(code);
        builder.msg(errorMessage);
        builder.timestamp(System.currentTimeMillis());
        return builder.build();
    }

    /**
     * 请求失败
     *
     * @param errorMessage 错误信息
     * @return Result
     */
    public static Result error(String errorMessage) {
        Result.ResultBuilder builder = Result.builder();
        builder.code(ResponseCodeEnum.SUCCESS.getValue());
        builder.msg(errorMessage);
        builder.timestamp(System.currentTimeMillis());
        return builder.build();
    }

}
