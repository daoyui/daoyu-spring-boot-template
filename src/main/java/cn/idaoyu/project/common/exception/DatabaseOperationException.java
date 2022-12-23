package cn.idaoyu.project.common.exception;

/**
 * @author 一条秋刀鱼zz
 * @className DatabaseOperationException
 * @description 数据库操作异常
 * @date 2022/12/24 0:30
 */
public class DatabaseOperationException extends RuntimeException {

    public DatabaseOperationException(String message) {
        super(message);
    }
}
