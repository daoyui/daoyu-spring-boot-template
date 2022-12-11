package cn.idaoyu.project.action.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "用户管理")
public class UserController {

    @PostMapping(value = "/register")
    @ApiOperation("注册")
    public void register() {

    }

}
