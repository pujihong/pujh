package com.hewei.pujh.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.sys.entity.SysRole;
import com.hewei.pujh.sys.mapper.SysRoleMapper;
import com.hewei.pujh.sys.service.ISysRoleService;
import com.hewei.pujh.sys.vo.RoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<RoleVo> getAllRoleList() {
        return roleMapper.getAllRoleList();
    }

    @Override
    public IPage<RoleVo> getRoleList(Integer pageNum, Integer pageSize, String name, Integer status) {
        return roleMapper.getRoleList(new Page<>(pageNum, pageSize), name, status);
    }

    @Override
    public boolean saveRole(Long roleId, String name, String code, Long userId) {
        SysRole role = new SysRole();
        role.setName(name);
        role.setCode(code);
        role.setStatus(0);
        if (roleId == null) {
            return roleMapper.insert(role) == 1;
        } else {
            role.setId(roleId);
            return roleMapper.updateById(role) == 1;
        }
    }
}
