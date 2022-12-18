package cn.idaoyu.project.action.user.service;

import cn.idaoyu.project.action.user.entity.TUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 一条秋刀鱼zz
 * @className TUserRoleService
 * @description 用户、角色关联表 service
 * @date 2022/12/18 16:03
 */
public interface TUserRoleService extends IService<TUserRole> {

    /**
     * 查询用户角色的id
     *
     * @param userId 用户id
     * @return List<TUserRole>
     */
    List<Long> queryRoleIdByUserId(Long userId);

}
