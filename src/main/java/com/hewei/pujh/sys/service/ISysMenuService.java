package com.hewei.pujh.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.sys.entity.SysMenu;
import com.hewei.pujh.sys.vo.LevelVo;
import com.hewei.pujh.sys.vo.MenuListVo;
import com.hewei.pujh.sys.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 查询用户nav菜单
     *
     * @param userId 用户id
     * @return List<MenuVo>
     */
    List<MenuVo> getUserMenuList(Long userId);

    /**
     * 查询菜单有几个级别
     *
     * @return List<LevelVo>
     */
    List<LevelVo> getMenuLevelList();

    IPage<MenuListVo> getMenuList(Integer pageNum, Integer pageSize, String name, Integer level, Integer status);

    boolean saveMenu(Long menuId, String name, Long userId);

    List<MenuVo> getRoleMenuList(Long roleId);

    List<MenuVo> getAllMenuList();
}
