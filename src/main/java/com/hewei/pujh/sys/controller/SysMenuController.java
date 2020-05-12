package com.hewei.pujh.sys.controller;


import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.sys.service.ISysMenuService;
import com.hewei.pujh.sys.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "菜单相关接口")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    @PostMapping(path = "/getUserMenu")
    @ApiOperation(value = "查询用户的菜单")
    public ResultModel getUserNavMenu(@CurrentUser UserVo user) {
        return ResultModel.success(menuService.getUserMenu(user.getId()));
    }
}
