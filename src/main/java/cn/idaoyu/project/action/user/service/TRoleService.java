package cn.idaoyu.project.action.user.service;

import cn.idaoyu.project.action.user.entity.TRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 一条秋刀鱼zz
 * @className TRoleService
 * @description 角色表 service
 * @date 2022/12/18 15:55
 */
public interface TRoleService extends IService<TRole> {

    /**
     * 查询角色的名字和状态
     *
     * @param roleId 角色id
     * @return List<TRole>
     */
    List<TRole> queryNameAndStatusById(List<Long> roleId);

}
