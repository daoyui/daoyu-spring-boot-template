package cn.idaoyu.project.common.security.handler;

import cn.hutool.extra.servlet.ServletUtil;
import cn.idaoyu.project.action.user.data.LoginResponse;
import cn.idaoyu.project.common.utils.JwtUtil;
import cn.idaoyu.project.common.utils.ResultUtil;
import com.google.common.collect.Maps;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 一条秋刀鱼zz
 * @className LoginAuthenticationSuccessHandler
 * @description 登录成功处理器
 * @date 2022/12/12 20:54
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponse.LoginResponseBuilder builder = LoginResponse.builder();
        // 生成 access token、refresh token
        Map<String, Object> userInfoMap = new LinkedHashMap<>();
        userInfoMap.put("username", userDetails.getUsername());
        userInfoMap.put("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        // 过期时间 2 小时
        String accessToken = JwtUtil.buildJwtToken(userInfoMap, 2 * 60);
        builder.accessToken(accessToken);
        // 将 accessToken 的值作为 refreshToken 的 payload
        Map<String, String> map = Maps.newHashMap();
        map.put("accessToken", accessToken);
        // 过期时间 24 小时
        String refreshToken = JwtUtil.buildJwtToken(map, 24 * 60);
        builder.refreshToken(refreshToken);
        ServletUtil.write(response, ResultUtil.isOk(builder.build()).toString(), "application/json;charset=utf-8");
    }
}
