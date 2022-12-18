package cn.idaoyu.project.action.user.service.impl;

import cn.idaoyu.project.action.user.entity.TRole;
import cn.idaoyu.project.action.user.mapper.TRoleMapper;
import cn.idaoyu.project.action.user.service.TRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz
 * @className TRoleServiceImpl
 * @description 角色表 service impl
 * @date 2022/12/18 15:55
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements TRoleService {


    @Override
    public List<TRole> queryNameAndStatusById(List<Long> roleId) {
        LambdaQueryWrapper<TRole> wrapper = Wrappers.lambdaQuery(TRole.class);
        wrapper.select(TRole::getName, TRole::getStatus);
        wrapper.in(TRole::getId, roleId);
        return super.list(wrapper);
    }
}
