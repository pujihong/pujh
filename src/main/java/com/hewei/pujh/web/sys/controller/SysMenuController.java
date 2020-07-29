package com.hewei.pujh.web.sys.controller;


import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.enums.ErrorCodeEnum;
import com.hewei.pujh.web.sys.service.ISysMenuService;
import com.hewei.pujh.web.sys.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @PostMapping(path = "/getUserMenuList")
    @ApiOperation(value = "查询用户的菜单",notes = "树形结构")
    public ResultModel getUserNavMenu(@ApiIgnore @CurrentUser UserVo user) {
        return ResultModel.success(menuService.getUserMenuList(user.getId()));
    }

    @GetMapping(path = "/getAllMenuList")
    @ApiOperation(value = "查询系统全部菜单",notes = "树形结构")
    public ResultModel getAllMenuList() {
        return ResultModel.success(menuService.getAllMenuList());
    }

    @PostMapping(path = "/getRoleMenuList")
    @ApiOperation(value = "查询角色的菜单")
    public ResultModel getRoleMenuList(@RequestParam Long roleId) {
        return ResultModel.success(menuService.getRoleMenuList(roleId));
    }

    @GetMapping(path = "/getMenuLevelList")
    @ApiOperation(value = "查询菜单的级别列表", notes = "最大支持到四级")
    public ResultModel getMenuLevelList() {
        return ResultModel.success(menuService.getMenuLevelList());
    }

    @PostMapping(path = "/getMenuList")
    @ApiOperation(value = "分页查询菜单信息列表", notes = "level 0一级菜单 1二级菜单,菜单最多4层")
    public ResultModel getMenuList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) Integer level,
                                   @RequestParam(required = false) Integer status,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResultModel.success(menuService.getMenuList(pageNum, pageSize, name, level, status));
    }

    @PostMapping(path = "/saveMenu")
    @ApiOperation(value = "保存菜单", notes = "只提供编辑菜单名称")
    public ResultModel saveMenu(@ApiIgnore @CurrentUser UserVo user,
                                @RequestParam Long menuId,
                                @RequestParam(required = false) String name) {
        boolean result = menuService.saveMenu(menuId, name, user.getId());
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ErrorCodeEnum.OP_FAILED_ERROR.getCode());
        }
    }
}
