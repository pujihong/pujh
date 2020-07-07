package com.hewei.pujh.sys.controller;


import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.enums.ErrorCodeEnum;
import com.hewei.pujh.sys.service.ISysRoleService;
import com.hewei.pujh.sys.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色相关接口")
public class SysRoleController {
    @Autowired
    private ISysRoleService roleService;

    @GetMapping(path = "/getAllRoleList")
    @ApiOperation(value = "查询所有的角色列表")
    public ResultModel getAllRoleList() {
        return ResultModel.success(roleService.getAllRoleList());
    }

    @PostMapping(path = "/getRoleList")
    @ApiOperation(value = "分页查询角色信息列表")
    public ResultModel getRoleList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) Integer status,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResultModel.success(roleService.getRoleList(pageNum, pageSize, name, status));
    }

    @PostMapping(path = "/saveRole")
    @ApiOperation(value = "保存角色", notes = "roleId有值更新，没值新增")
    public ResultModel saveRole(@ApiIgnore @CurrentUser UserVo user,
                                @RequestParam(required = false) Long roleId,
                                @RequestParam String name,
                                @RequestParam String code) {
        if (StringUtils.isAnyBlank(name, code)) {
            return ResultModel.error(ErrorCodeEnum.WRONG_PARAMS_ERROR.getCode());
        }
        boolean result = roleService.saveRole(roleId, name, code, user.getId());
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ErrorCodeEnum.OP_FAILED_ERROR.getCode());
        }
    }

    @PostMapping(path = "/saveRoleMenu")
    @ApiOperation(value = "保存角色授权", notes = "roleId有值更新，没值新增")
    public ResultModel saveRoleMenu(@ApiIgnore @CurrentUser UserVo user,
                                    @RequestParam Long roleId,
                                    @RequestParam List<Long> menuIds) {
        boolean result = roleService.saveRoleMenu(menuIds, roleId, user.getId());
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ErrorCodeEnum.OP_FAILED_ERROR.getCode());
        }
    }
}
