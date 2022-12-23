package cn.idaoyu.project.common.security.filter;

import cn.hutool.core.util.StrUtil;
import cn.idaoyu.project.common.security.service.impl.JwtUserDetailsServiceImpl;
import cn.idaoyu.project.common.security.utils.TokenStatus;
import cn.idaoyu.project.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 一条秋刀鱼zz
 * @className CheckJwtTokenFilter
 * @description 校验 jwt token 过滤器
 * @date 2022/12/18 16:56
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckJwtTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER_NAME = "Authentication";
    private final JwtUserDetailsServiceImpl jwtUserDetailsService;
    private final TokenStatus tokenStatus;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(TOKEN_HEADER_NAME);
        // 如果用户携带 token 请求 并 token 有效...
        if (StrUtil.isNotBlank(token)) {
            String username = checkTokenAndGetUsername(token);
            if (username != null) {
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null
                        , userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验token 并获取用户名（账号）
     *
     * @param token jwt token
     * @return 用户名
     */
    private String checkTokenAndGetUsername(String token) {
        int status = JwtUtil.checkJwtToken(token);
        switch (status) {
            case 1:
                // token 有效但过期了
                tokenStatus.setTokenStatus(true, false);
                return null;
            case 2:
                // token 有效且没过期
                tokenStatus.setTokenStatus(true, true);
                return JwtUtil.getUsername(token);
            default:
                // 默认 token 无效
                tokenStatus.setTokenStatus(false, false);
                return null;
        }
    }

}
