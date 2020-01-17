package com.hewei.hewei.sys.controller;

import com.hewei.hewei.annotation.SecretAnnotation;
import com.hewei.hewei.base.Constant;
import com.hewei.hewei.base.RedisService;
import com.hewei.hewei.base.ResultModel;
import com.hewei.hewei.sys.entity.SysUser;
import com.hewei.hewei.sys.service.ISysUserService;
import com.hewei.hewei.utils.PassWordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
@Api(description = "用户相关接口")
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
    public ResultModel logout(HttpServletRequest request) {
        redisService.deleteToken(request.getHeader(Constant.AUTHORIZATION));
        return ResultModel.success();
    }

}
