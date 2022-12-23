package cn.idaoyu.project.config;

import cn.idaoyu.project.common.annotation.IgnoreResponseAdvice;
import cn.idaoyu.project.common.utils.ResultUtil;
import cn.idaoyu.project.common.vo.Result;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author 一条秋刀鱼zz
 * @className CommonResponseDataAdvice
 * @description 全局统一返回结果包装
 * @date 2022/12/23 23:57
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 检查注解是否存在
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        Method method = returnType.getMethod();
        if (method != null && method.isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType
            , ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return ResultUtil.isOk();
        }
        if (body instanceof Result) {
            return body;
        }
        if (body instanceof String) {
            return JSONObject.toJSONString(ResultUtil.isOk(body));
        }
        return ResultUtil.isOk(body);
    }
}
