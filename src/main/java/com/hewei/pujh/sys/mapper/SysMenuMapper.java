package com.hewei.pujh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hewei.pujh.sys.entity.SysMenu;
import com.hewei.pujh.sys.vo.MenuVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 查询用户可用的nav菜单
     * @param userId 用户id
     * @return List<MenuVo>
     */
    @Select({
            "select m.id,m.parent_id as parentId,m.`name`,m.url,m.icon from sys_menu m",
            "LEFT JOIN sys_role_menu rm on m.id = rm.menu_id",
            "LEFT JOIN sys_role r on rm.role_id = r.id",
            "LEFT JOIN sys_user_role ur on r.id = ur.role_id",
            "where m.deleted = 0 and m.type = 0 and m.`status` = 0",
            "and rm.deleted = 0 and r.deleted = 0 and ur.deleted = 0 and ur.user_id = #{userId}",
            "order by m.level,m.sort"
    })
    @ResultType(MenuVo.class)
    List<MenuVo> getUserMenu(Long userId);

    /**
     * 查询所有可用的nav菜单
     * @return List<MenuVo>
     */
    @Select({
       "select id,parent_id as parentId,`name`,url,icon from sys_menu where deleted = 0 and type = 0 and status = 0",
       "order by sort"
    })
    @ResultType(MenuVo.class)
    List<MenuVo> getAllNavMenu();
}
