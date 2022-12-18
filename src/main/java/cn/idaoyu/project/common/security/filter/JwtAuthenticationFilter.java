package cn.idaoyu.project.common.security.filter;

import cn.hutool.extra.servlet.ServletUtil;
import cn.idaoyu.project.common.security.vo.UserLoginInfoVo;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 一条秋刀鱼zz
 * @className JwtAuthenticationFilter
 * @description 登录认证的Filter，用来处理 spring security 只支持表单提交账号密码的问题
 * @date 2022/12/12 19:55
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    public JwtAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("不支持的请求方式：" + request.getMethod());
        }
        UserLoginInfoVo userLoginInfoVo;
        try {
            String requestJSONString = ServletUtil.getBody(request);
            userLoginInfoVo = JSONObject.parseObject(requestJSONString, UserLoginInfoVo.class);
        } catch (Exception e) {
            throw new AuthenticationServiceException("不支持的请求数据类型！");
        }

        String username = userLoginInfoVo.getUsername() != null ? userLoginInfoVo.getUsername() : "";
        String password = userLoginInfoVo.getPassword() != null ? userLoginInfoVo.getPassword() : "";
        // TODO 未来可能会增加验证码校验
        String captcha = userLoginInfoVo.getCaptcha();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return super.getAuthenticationManager().authenticate(authRequest);
    }
}
