package com.hewei.pujh.sys.controller;

import com.hewei.pujh.annotation.SecretAnnotation;
import com.hewei.pujh.base.Constant;
import com.hewei.pujh.base.RedisService;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.service.ISysUserService;
import com.hewei.pujh.utils.PassWordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            return ResultModel.error(ResultModel.USER_NOT_EXIST_ERROR);
        }
        if (!PassWordUtil.encodePassword(user.getUsername(), password).equals(user.getPassword())) {
            return ResultModel.error(ResultModel.USERNAME_OR_PASSWORD_ERROR);
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

}
