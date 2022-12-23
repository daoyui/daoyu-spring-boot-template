package cn.idaoyu.project.common.exception;

/**
 * @author 一条秋刀鱼zz
 * @className BusinessException
 * @description 一般业务异常
 * @date 2022/12/24 2:22
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
