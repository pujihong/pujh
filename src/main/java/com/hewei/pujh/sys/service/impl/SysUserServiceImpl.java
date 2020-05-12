package com.hewei.pujh.sys.service.impl;

import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.mapper.SysUserMapper;
import com.hewei.pujh.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.sys.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-01-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Resource
    private SysUserMapper userMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public UserVo getUserVoById(Long currentUserId) {
        return userMapper.getUserVoById(currentUserId);
    }
}
