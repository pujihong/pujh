package com.hewei.pujh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hewei.pujh.sys.entity.SysMenu;
import com.hewei.pujh.sys.vo.LevelVo;
import com.hewei.pujh.sys.vo.MenuListVo;
import com.hewei.pujh.sys.vo.MenuVo;
import org.apache.ibatis.annotations.Param;
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
     *
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
    List<MenuVo> getUserMenuList(Long userId);

    @Select({
            "select m.id,m.parent_id as parentId,m.`name`,m.url,m.icon from sys_menu m",
            "LEFT JOIN sys_role_menu rm on m.id = rm.menu_id",
            "where m.deleted = 0 and m.type = 0 and m.`status` = 0 and rm.deleted = 0 and rm.role_id = #{roleId}",
            "order by m.level,m.sort"
    })
    List<MenuVo> getRoleMenuList(Long roleId);

    /**
     * 查询所有可用的nav菜单
     *
     * @return List<MenuVo>
     */
    @Select({
            "select id,parent_id as parentId,`name`,url,icon from sys_menu where deleted = 0 and type = 0 and status = 0",
            "order by sort"
    })
    @ResultType(MenuVo.class)
    List<MenuVo> getAllNavMenu();

    @Select({
            "select DISTINCT case",
            "WHEN level = 0 THEN '一级'",
            "WHEN level = 1 THEN '二级'",
            "WHEN level = 2 THEN '三级'",
            "WHEN level = 3 THEN '四级'",
            "END as `name`, `level`",
            "from sys_menu where deleted = 0",
            "ORDER BY `level`"
    })
    List<LevelVo> getMenuLevelList();


    @Select({
            "<script> ",
            "select id,`name`,icon,`level`,`status`,",
            "case",
            "WHEN level = 0 THEN '一级'",
            "WHEN level = 1 THEN '二级'",
            "WHEN level = 2 THEN '三级'",
            "WHEN level = 3 THEN '四级'",
            "END as levelName",
            "from sys_menu",
            "where deleted = 0",
            "<if test='name != null and name != &apos;&apos;'> and name like concat('%', #{name}, '%')</if>",
            "<if test='level != null'> and level = #{level}</if>",
            "<if test='status != null'> and status = #{status}</if>",
            "ORDER BY `level`",
            "</script> "
    })
    IPage<MenuListVo> getMenuList(@Param("objectPage") Page<Object> objectPage, @Param("name") String name, @Param("level") Integer level, @Param("status") Integer status);

}
