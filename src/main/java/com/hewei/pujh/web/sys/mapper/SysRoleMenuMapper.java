package com.hewei.pujh.web.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hewei.pujh.web.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-06-16
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {


    @Insert({
            "<script> ",
            "INSERT INTO sys_role_menu(create_user, role_id,menu_id) values",
            "<foreach  collection = 'addRoleMenuList' item = 'item'  separator= ','  >",
            "(#{item.createUser},#{item.roleId},#{item.menuId})",
            "</foreach>",
            "</script> "
    })
    void batchInsert(@Param("addRoleMenuList") List<SysRoleMenu> addRoleMenuList);

    @Update({
            "<script> ",
            "update sys_role_menu set deleted = 1, update_user=#{userId} where deleted = 0 and role_id = #{roleId} and menu_id in",
            "<foreach  collection = 'removeArr' item = 'id' index = 'index' open = '(' separator= ',' close = ')' >",
            "#{id}",
            "</foreach>",
            "</script> "
    })
    void deleteBatchRoleMenu(@Param("removeArr") List<Long> removeArr, @Param("roleId") Long roleId, @Param("userId") Long userId);
}
