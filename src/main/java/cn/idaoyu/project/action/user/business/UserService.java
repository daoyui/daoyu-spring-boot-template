package cn.idaoyu.project.action.user.business;

import cn.idaoyu.project.action.user.data.RefreshTokenParams;
import cn.idaoyu.project.action.user.data.RegisterParams;
import cn.idaoyu.project.action.user.data.RegisterResponse;
import cn.idaoyu.project.action.user.service.TUserRoleService;
import cn.idaoyu.project.action.user.service.TUserService;
import cn.idaoyu.project.common.exception.DatabaseOperationException;
import cn.idaoyu.project.common.security.service.impl.JwtUserDetailsServiceImpl;
import cn.idaoyu.project.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 一条秋刀鱼zz
 * @className UserService
 * @description 用户 business
 * @date 2022/12/24 0:14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final TUserService userService;
    private final TUserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUserDetailsServiceImpl jwtUserDetailsService;

    public RegisterResponse register(RegisterParams params) {
        String password = passwordEncoder.encode(params.getPassword());
        Long userId = userService.createUser(params.getUsername(), password, params.getNickname());
        if (userId == null || userId < 1L) {
            throw new DatabaseOperationException("注册失败，请稍后重试");
        }
        boolean insertSuccess = userRoleService.insertDefaultRole(userId, params.getUsername());
        if (!insertSuccess) {
            throw new DatabaseOperationException("注册失败，请稍后重试");
        }

        return RegisterResponse.builder().message("注册成功").build();
    }

    public String refreshToken(RefreshTokenParams params) throws IOException {
        // 校验刷新令牌
        int status = JwtUtil.checkJwtToken(params.getToken(), "accessToken");
        if (status != 2) {
            throw new CredentialsExpiredException("token过期，请重新登录");
        }
        // 获取旧的访问令牌
        String oldAccessToken = JwtUtil.getClaim(params.getToken(), "accessToken");
        int oldAccessTokenStatus = JwtUtil.checkJwtToken(oldAccessToken, "username");
        if (oldAccessTokenStatus == 0) {
            throw new CredentialsExpiredException("token过期，请重新登录");
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(JwtUtil.getClaim(oldAccessToken, "username"));
        Map<String, Object> userInfoMap = new LinkedHashMap<>();
        userInfoMap.put("username", userDetails.getUsername());
        userInfoMap.put("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        // 过期时间 2 小时
        return JwtUtil.buildJwtToken(userInfoMap, 2 * 60);
    }
}
