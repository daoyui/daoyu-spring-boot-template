package cn.idaoyu.project.action.user.service.impl;

import cn.idaoyu.project.action.user.entity.TUser;
import cn.idaoyu.project.action.user.mapper.TUserMapper;
import cn.idaoyu.project.action.user.service.TUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 一条秋刀鱼zz
 * @className TUserServiceImpl
 * @description 用户表 service impl
 * @date 2022/12/18 15:44
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Override
    public TUser queryByUsername(String username) {
        LambdaQueryWrapper<TUser> wrapper = Wrappers.lambdaQuery(TUser.class);
        wrapper.eq(TUser::getUsername, username);
        return super.getOne(wrapper);
    }

    @Override
    public Long createUser(String username, String password, String nickname) {
        TUser.TUserBuilder builder = TUser.builder();
        builder.username(username);
        builder.password(password);
        builder.nickname(nickname);
        builder.isAccountNonExpired(true);
        builder.isAccountNonLocked(true);
        builder.isCredentialsNonExpired(true);
        builder.isEnabled(true);
        Date now = new Date();
        builder.createdTime(now);
        builder.updateTime(now);
        TUser tUser = builder.build();
        boolean saveSuccess = super.save(tUser);
        if (saveSuccess) {
            return tUser.getId();
        }
        return null;
    }
}
