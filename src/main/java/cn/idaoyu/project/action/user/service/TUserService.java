package cn.idaoyu.project.action.user.service;

import cn.idaoyu.project.action.user.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 一条秋刀鱼zz
 * @className TUserService
 * @description 用户表 service
 * @date 2022/12/18 15:43
 */
public interface TUserService extends IService<TUser> {

    /**
     * 查询用户信息
     *
     * @param username 用户名（账号）
     * @return TUser
     */
    TUser queryByUsername(String username);

    /**
     * 创建用户
     *
     * @param username 用户名
     * @param password 密码（加密后的）
     * @param nickname 昵称
     * @return 是否成功
     */
    Long createUser(String username, String password, String nickname);

}
