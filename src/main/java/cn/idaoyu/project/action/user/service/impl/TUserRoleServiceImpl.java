package cn.idaoyu.project.action.user.service.impl;

import cn.idaoyu.project.action.user.entity.TUserRole;
import cn.idaoyu.project.action.user.mapper.TUserRoleMapper;
import cn.idaoyu.project.action.user.service.TUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 一条秋刀鱼zz
 * @className TUserRoleServiceImpl
 * @description 用户、角色关联表 service impl
 * @date 2022/12/18 16:04
 */
@Service
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRole> implements TUserRoleService {

    @Override
    public List<Long> queryRoleIdByUserId(Long userId) {
        LambdaQueryWrapper<TUserRole> wrapper = Wrappers.lambdaQuery(TUserRole.class);
        wrapper.select(TUserRole::getRoleId);
        wrapper.eq(TUserRole::getUserId, userId);
        return super.list(wrapper).stream().map(TUserRole::getRoleId).collect(Collectors.toList());
    }
}
