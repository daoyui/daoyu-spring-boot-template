package cn.idaoyu.project.action.user.business;

import cn.idaoyu.project.action.user.data.RegisterParams;
import cn.idaoyu.project.action.user.data.RegisterResponse;
import cn.idaoyu.project.action.user.service.TUserRoleService;
import cn.idaoyu.project.action.user.service.TUserService;
import cn.idaoyu.project.common.exception.DatabaseOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
