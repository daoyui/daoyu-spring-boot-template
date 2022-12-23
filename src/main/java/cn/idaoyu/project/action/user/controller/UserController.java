package cn.idaoyu.project.action.user.controller;

import cn.idaoyu.project.action.user.business.UserService;
import cn.idaoyu.project.action.user.data.RegisterParams;
import cn.idaoyu.project.action.user.data.RegisterResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 一条秋刀鱼zz
 * @className UserController
 * @description 用户接口
 * @date 2022/12/11 16:33
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "用户管理")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    @ApiOperation("注册")
    public RegisterResponse register(@Validated @RequestBody RegisterParams params) {
        return userService.register(params);
    }

}
