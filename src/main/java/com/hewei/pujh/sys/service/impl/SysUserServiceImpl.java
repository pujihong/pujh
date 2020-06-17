package com.hewei.pujh.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.entity.SysUserRole;
import com.hewei.pujh.sys.mapper.SysRoleMapper;
import com.hewei.pujh.sys.mapper.SysUserMapper;
import com.hewei.pujh.sys.mapper.SysUserRoleMapper;
import com.hewei.pujh.sys.service.ISysUserService;
import com.hewei.pujh.sys.vo.RoleVo;
import com.hewei.pujh.sys.vo.UserVo;
import com.hewei.pujh.utils.PassWordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Resource
    private SysRoleMapper roleMapper;


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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(Long userId, String name, List<Long> roleIds, Long currentUserId) {
        List<SysUserRole> userRoleList = new ArrayList<>();
        SysUserRole userRole;
        try {
            // 新增
            if (userId == null) {
                SysUser user = new SysUser();
                user.setCreateUser(currentUserId);
                user.setUsername(name);
                user.setPassword(PassWordUtil.initPassword(name));
                userMapper.insert(user);
                Long newUserId = user.getId();
                for (Long roleId : roleIds) {
                    userRole = new SysUserRole();
                    userRole.setCreateUser(currentUserId);
                    userRole.setUserId(newUserId);
                    userRole.setRoleId(roleId);
                    userRoleList.add(userRole);
                }
                userRoleMapper.batchInsert(userRoleList);
            } else {
                SysUser user = new SysUser();
                user.setId(userId);
                user.setUsername(name);
                user.setUpdateUser(currentUserId);
                userMapper.updateById(user);
                // 判断出哪些是新增的，那些是删除的
                List<RoleVo> roleList = roleMapper.getRoleListByUserId(userId);
                // 原有的角色
                List<Long> roleArr = new ArrayList<>();
                // 删除的角色
                List<Long> removeArr = new ArrayList<>();
                for (RoleVo role : roleList) {
                    roleArr.add(role.getId());
                }
                for (Long roleId : roleIds) {
                    if (!roleArr.contains(roleId)) {
                        userRole = new SysUserRole();
                        userRole.setCreateUser(currentUserId);
                        userRole.setUserId(userId);
                        userRole.setRoleId(roleId);
                        userRoleList.add(userRole);
                    }
                }
                for (Long roleId : roleArr) {
                    if (!roleIds.contains(roleId)) {
                        removeArr.add(roleId);
                    }
                }
                if (removeArr.size() > 0) {
                    userRoleMapper.deleteBatchUserRole(removeArr, userId, currentUserId);
                }
                if (userRoleList.size() > 0) {
                    userRoleMapper.batchInsert(userRoleList);
                }
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("保存用户失败", e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUserById(Long userId, Long currentUserId) {
        try {
            // 先逻辑删除userRole信息
            userRoleMapper.deleteUserRole(userId, currentUserId);
            SysUser user = new SysUser();
            user.setId(userId);
            user.setUpdateUser(currentUserId);
            user.setDeleted(1);
            userMapper.updateById(user);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("删除用户失败", e);
            return false;
        }
    }
}
