package com.hewei.hewei.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.hewei.sys.entity.SysUser;
import com.hewei.hewei.sys.vo.UserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author pujihong
 * @since 2020-01-15
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询user
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser getUserByUsername(String username);

    /**
     * 根据id查询user
     *
     * @param currentUserId 当前用户id
     * @return UserVo
     */
    UserVo getUserVoById(Long currentUserId);
}
