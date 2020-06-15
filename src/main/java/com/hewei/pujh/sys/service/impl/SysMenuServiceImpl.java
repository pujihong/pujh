package com.hewei.pujh.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.sys.entity.SysMenu;
import com.hewei.pujh.sys.mapper.SysMenuMapper;
import com.hewei.pujh.sys.service.ISysMenuService;
import com.hewei.pujh.sys.vo.LevelVo;
import com.hewei.pujh.sys.vo.MenuListVo;
import com.hewei.pujh.sys.vo.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<MenuVo> getUserMenu(Long userId) {
        List<MenuVo> menuList = new ArrayList<>();
        if (userId == 0) {
            menuList = menuMapper.getAllNavMenu();
        } else {
            menuList = menuMapper.getUserMenu(userId);
        }
        if (menuList == null || menuList.size() == 0) {
            return null;
        }
        // 构建children
        List<MenuVo> menuVoList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (MenuVo menu : menuList) {
            // 一级菜单没有parentId
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menuVoList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuVo menu : menuVoList) {
            menu.setChildren(getChild(menu.getId(), menuList));
        }
        return menuVoList;
    }

    @Override
    public List<LevelVo> getMenuLevelList() {
        return menuMapper.getMenuLevelList();
    }

    @Override
    public IPage<MenuListVo> getMenuList(Integer pageNum, Integer pageSize, String name, Integer level, Integer status) {
        return menuMapper.getMenuList(new Page<>(pageNum, pageSize), name, level, status);
    }

    @Override
    public boolean saveMenu(Long menuId, String name, Long userId) {
        SysMenu menu = new SysMenu();
        menu.setUpdateUser(userId);
        menu.setName(name);
        menu.setId(menuId);
        return menuMapper.updateById(menu) == 1;
    }

    private List<MenuVo> getChild(Long menuId, List<MenuVo> rootMenu) {
        // 子菜单
        List<MenuVo> childList = new ArrayList<>();
        for (MenuVo menuVo : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menuVo.getParentId() != null) {
                if (menuVo.getParentId().equals(menuId)) {
                    childList.add(menuVo);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuVo menuVo : childList) {// 没有url子菜单还有子菜单
            // 递归
            menuVo.setChildren(getChild(menuVo.getId(), rootMenu));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
