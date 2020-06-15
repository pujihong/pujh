package com.hewei.pujh.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.mapper.SysUserMapper;
import com.hewei.pujh.sys.service.ISysUserService;
import com.hewei.pujh.sys.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public IPage<UserVo> getUserList(Integer pageNum, Integer pageSize, String name, List<Long> roleIds) {
        return userMapper.getUserList(new Page<>(pageNum, pageSize), name, roleIds);
    }
}
