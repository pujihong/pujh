package com.hewei.pujh.web.sys.controller;

import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.annotation.SecretAnnotation;
import com.hewei.pujh.base.Constant;
import com.hewei.pujh.base.RedisService;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.enums.ErrorCodeEnum;
import com.hewei.pujh.web.sys.entity.SysUser;
import com.hewei.pujh.web.sys.service.ISysUserService;
import com.hewei.pujh.web.sys.vo.UserVo;
import com.hewei.pujh.utils.PassWordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author pujihong
 * @since 2020-01-15
 */
@RestController
@Api(tags = "用户相关接口")
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private RedisService redisService;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return map
     */
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "加密后的用户名", required = true),
            @ApiImplicitParam(name = "password", value = "加密后的用户密码", required = true),
    })
    @PostMapping(value = "/login")
    @SecretAnnotation(decode = true)
    public ResultModel login(@RequestParam String username,
                             @RequestParam String password) {
        SysUser user = userService.getUserByUsername(username);
        if (user == null) {
            return ResultModel.error(ErrorCodeEnum.USER_NOT_EXIST_ERROR.getCode());
        }
        if (!PassWordUtil.encodePassword(user.getUsername(), password).equals(user.getPassword())) {
            return ResultModel.error(ErrorCodeEnum.USERNAME_OR_PASSWORD_ERROR.getCode());
        }
        // 不让用户多处登录
        String token = redisService.getTokenByUserId(user.getId());
        if (StringUtils.isNotBlank(token)) {
            redisService.deleteToken(token);
        }
        //生成一个token，保存用户登录状态
        token = redisService.createToken(user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return ResultModel.success(map);
    }

    @GetMapping(path = "/logout")
    @ApiOperation(value = "登出")
    public ResultModel logout(HttpServletRequest request) {
        redisService.deleteToken(request.getHeader(Constant.AUTHORIZATION));
        return ResultModel.success();
    }

    @PostMapping(path = "/getUserList")
    @ApiOperation(value = "分页查询用户列表", notes = "角色支持多选")
    public ResultModel getUserList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) List<Long> roleIds,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResultModel.success(userService.getUserList(pageNum, pageSize, name, roleIds));
    }

    @PostMapping(path = "/saveUser")
    @ApiOperation(value = "保存用户", notes = "userId有值更新，没值新增, 用户 角色 一对多")
    public ResultModel saveUser(@ApiIgnore @CurrentUser UserVo user,
                                @RequestParam(required = false) Long userId,
                                @RequestParam String name,
                                @RequestParam List<Long> roleIds) {
        SysUser sysUser = userService.getUserByUsername(name);
        if (StringUtils.isAnyBlank(name) || roleIds == null || roleIds.size() == 0) {
            return ResultModel.error(ErrorCodeEnum.WRONG_PARAMS_ERROR.getCode());
        }
        // 新增
        if (userId == null && sysUser != null) {
            return ResultModel.error("用户名已存在");
        }
        if (sysUser != null && name.equals(sysUser.getUsername()) && !userId.equals(sysUser.getId())) {
            return ResultModel.error("用户名已存在");
        }
        boolean result = userService.saveUser(userId, name, roleIds, user.getId());
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ErrorCodeEnum.OP_FAILED_ERROR.getCode());
        }
    }

    @PostMapping(path = "/deleteUserById")
    @ApiOperation(value = "删除用户")
    public ResultModel deleteUserById(@ApiIgnore @CurrentUser UserVo user,
                                      @RequestParam Long userId) {
        boolean result = userService.deleteUserById(userId, user.getId());
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ErrorCodeEnum.OP_FAILED_ERROR.getCode());
        }
    }

}
