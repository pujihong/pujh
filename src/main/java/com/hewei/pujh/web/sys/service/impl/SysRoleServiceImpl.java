package com.hewei.pujh.web.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.web.sys.entity.SysRole;
import com.hewei.pujh.web.sys.entity.SysRoleMenu;
import com.hewei.pujh.web.sys.mapper.SysMenuMapper;
import com.hewei.pujh.web.sys.mapper.SysRoleMapper;
import com.hewei.pujh.web.sys.mapper.SysRoleMenuMapper;
import com.hewei.pujh.web.sys.service.ISysRoleService;
import com.hewei.pujh.web.sys.vo.MenuVo;
import com.hewei.pujh.web.sys.vo.RoleVo;
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
 * 角色表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysMenuMapper menuMapper;
    @Resource
    private SysRoleMenuMapper roleMenuMapper;

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
            role.setCreateUser(userId);
            return roleMapper.insert(role) == 1;
        } else {
            role.setId(roleId);
            return roleMapper.updateById(role) == 1;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleMenu(List<Long> menuIds, Long roleId, Long userId) {
        // 原有的菜单列表
        List<MenuVo> list = menuMapper.getRoleMenuList(roleId);
        // 原有的菜单
        List<Long> menuArr = new ArrayList<>();
        // 新增的菜单
        List<SysRoleMenu> addRoleMenuList = new ArrayList<>();
        SysRoleMenu roleMenu;
        // 删除的菜单
        List<Long> removeArr = new ArrayList<>();
        for (MenuVo menu : list) {
            menuArr.add(menu.getId());
        }
        for (Long menuId : menuIds) {
            if (!menuArr.contains(menuId)) {
                roleMenu = new SysRoleMenu();
                roleMenu.setCreateUser(userId);
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                addRoleMenuList.add(roleMenu);
            }
        }
        for (Long menuId : menuArr) {
            if (!menuIds.contains(menuId)) {
                removeArr.add(menuId);
            }
        }
        try {
            if(removeArr.size() > 0) {
                roleMenuMapper.deleteBatchRoleMenu(removeArr, roleId, userId);
            }
            if (addRoleMenuList.size() > 0) {
                roleMenuMapper.batchInsert(addRoleMenuList);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("角色授权失败", e);
            return false;
        }
    }
}
