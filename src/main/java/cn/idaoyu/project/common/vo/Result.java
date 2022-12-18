package cn.idaoyu.project.common.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Builder;
import lombok.Data;

/**
 * @author 一条秋刀鱼zz
 * @className Result
 * @description 全局统一返回对象
 * @date 2022/12/12 20:58
 */
@Data
@Builder
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    private Long timestamp;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
