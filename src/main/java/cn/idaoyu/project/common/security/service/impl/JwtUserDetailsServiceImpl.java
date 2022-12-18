package cn.idaoyu.project.common.security.service.impl;

import cn.idaoyu.project.action.user.entity.TRole;
import cn.idaoyu.project.action.user.entity.TUser;
import cn.idaoyu.project.action.user.service.TRoleService;
import cn.idaoyu.project.action.user.service.TUserRoleService;
import cn.idaoyu.project.action.user.service.TUserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static cn.idaoyu.project.action.user.enums.UserRoleStatusEnum.IN_USE;
import static cn.idaoyu.project.action.user.enums.UserRoleStatusEnum.LOCKING;

/**
 * @author 一条秋刀鱼zz
 * @className JwtUserDetailsServiceImpl
 * @description 从数据库中加载用户信息
 * @date 2022/12/14 19:24
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final TUserService tUserService;
    private final TUserRoleService tUserRoleService;
    private final TRoleService tRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户数据
        TUser tUser = tUserService.queryByUsername(username);
        if (tUser == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 查询用户的角色的id
        List<Long> tUserRoleList = tUserRoleService.queryRoleIdByUserId(tUser.getId());
        // 查询用户所有的所属角色
        List<TRole> tRoleList = tRoleService.queryNameAndStatusById(tUserRoleList);
        // 对用户所属的角色状态进行处理
        List<String> roleList = filterRoleStatus(tRoleList, tUser);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(roleList.toArray(new String[0]));
        tUser.setAuthorities(authorityList);
        return tUser;
    }

    /**
     * 处理关于用户所属角色
     *
     * @param tRoleList 用户的角色
     * @param tUser     用户
     * @return 用户的所有角色
     */
    private List<String> filterRoleStatus(List<TRole> tRoleList, TUser tUser) {
        AtomicReference<Integer> allRoleCount = new AtomicReference<>(0);
        // 用户所属角色的全部状态
        List<String> inUseRoleNameList = Lists.newArrayList();
        // 得到用户所属角色的异常状态
        List<Integer> abnormalState = tRoleList.stream()
                .peek(v -> {
                    if (IN_USE.getValue().equals(v.getStatus())) {
                        inUseRoleNameList.add(v.getName());
                    }
                })
                .map(TRole::getStatus)
                .distinct()
                .peek(v -> allRoleCount.getAndSet(allRoleCount.get() + 1))
                .filter(v -> !IN_USE.getValue().equals(v))
                .collect(Collectors.toList());
        // 如果用户存在正常的角色，则返回正常的角色名字
        if (abnormalState.size() == allRoleCount.get()) {
            for (Integer status : abnormalState) {
                if (LOCKING.getValue().equals(status)) {
                    // 角色被锁定
                    tUser.setIsAccountNonLocked(false);
                }
            }
        }
        return inUseRoleNameList;
    }
}
