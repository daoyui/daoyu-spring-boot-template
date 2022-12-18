package cn.idaoyu.project.common.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import cn.idaoyu.project.common.enums.SecurityExceptionEnum;
import cn.idaoyu.project.common.utils.ResultUtil;
import cn.idaoyu.project.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 一条秋刀鱼zz
 * @className LoginAuthenticationFailureHandler
 * @description 登录成功处理器
 * @date 2022/12/12 21:14
 */
@Slf4j
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 根据异常类来返回不同的错误信息
        Result result = null;
        if (exception instanceof BadCredentialsException) {
            // 用户名或者密码错误
            result = ResultUtil.error(SecurityExceptionEnum.USERNAME_PASSWORD_ERROR.getErrorCode()
                    , SecurityExceptionEnum.USERNAME_PASSWORD_ERROR.getDescribe());
        }
        ServletUtil.write(response, result != null ? result.toString() : ResultUtil.error("登录失败，请联系管理员").toString()
                , "application/json;charset=utf-8");
        if (result == null) {
            log.warn("Spring Security 未处理的异常登录，异常类名：{}", exception.getClass().getName());
        }
    }
}
