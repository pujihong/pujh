package com.hewei.pujh.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.vo.UserVo;

import java.util.List;

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

    IPage<UserVo> getUserList(Integer pageNum, Integer pageSize, String name, List<Long> roleIds);
}
