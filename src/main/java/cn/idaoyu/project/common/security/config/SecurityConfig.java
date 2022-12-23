package cn.idaoyu.project.common.security.config;

import cn.hutool.extra.servlet.ServletUtil;
import cn.idaoyu.project.common.security.filter.CheckJwtTokenFilter;
import cn.idaoyu.project.common.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static cn.idaoyu.project.common.enums.SecurityExceptionEnum.LOGIN_REQUIRED;
import static cn.idaoyu.project.common.enums.SecurityExceptionEnum.NO_ACCESS;

/**
 * @author 一条秋刀鱼zz
 * @className SecurityConfig
 * @description Spring Security 全局配置
 * @date 2022/12/18 17:25
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    private final CheckJwtTokenFilter checkJwtTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //禁用表单登录，前后端分离用不上
                .disable()
                //应用登录过滤器的配置，配置分离
                .apply(jwtAuthenticationSecurityConfig)

                .and()
                // 设置URL的授权
                .authorizeRequests()
                // 这里需要将登录页面放行,permitAll()表示不再拦截，/login 登录的url，/refreshToken刷新token的url
                //TODO 此处正常项目中放行的url还有很多，比如swagger相关的url，druid的后台url，一些静态资源
                .antMatchers("/user/register", "/user/login", "/user/refreshToken")
                .permitAll()
                // 所有请求
                .anyRequest()
                // 必须被认证
                .authenticated()

                //处理异常情况：认证失败和权限不足
                .and()
                .exceptionHandling()
                //认证未通过，不允许访问异常处理器
                .authenticationEntryPoint((req, resp, authException) -> ServletUtil.write(resp
                        , ResultUtil.error(LOGIN_REQUIRED.getErrorCode(), LOGIN_REQUIRED.getDescribe()).toString()
                        , "application/json;charset=utf-8"))
                //认证通过，但是没权限处理器
                .accessDeniedHandler((req, resp, accessDeniedException) -> ServletUtil.write(resp
                        , ResultUtil.error(NO_ACCESS.getErrorCode(), NO_ACCESS.getDescribe()).toString()
                        , "application/json;charset=utf-8"))
                .and()
                //禁用session，JWT校验不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                //将TOKEN校验过滤器配置到过滤器链中，否则不生效，放到UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(checkJwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 关闭csrf
                .csrf().disable();
    }
}
