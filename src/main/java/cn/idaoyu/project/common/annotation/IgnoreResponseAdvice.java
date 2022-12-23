package cn.idaoyu.project.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 一条秋刀鱼zz
 * @className IgnoreResponseAdvice
 * @description 不会对被该注解标记的接口返回的数据进行包装
 * @date 2022/12/24 0:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IgnoreResponseAdvice {
}
