package com.hewei.pujh.web.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hewei.pujh.web.sys.entity.SysRole;
import com.hewei.pujh.web.sys.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select({
            "select id,name,status,code from sys_role where deleted = 0 and status = 0"
    })
    List<RoleVo> getAllRoleList();


    @Select({
            "<script> ",
            "select id,`name`,`status`,`code` from sys_role",
            "where deleted = 0",
            "<if test='name != null and name != &apos;&apos;'> and name like concat('%', #{name}, '%')</if>",
            "<if test='status != null'> and status = #{status}</if>",
            "ORDER BY create_time desc",
            "</script> "
    })
    IPage<RoleVo> getRoleList(@Param("objectPage") Page<Object> objectPage, @Param("name") String name, @Param("status") Integer status);

    @Select({
            "select r.id,r.name,r.status,r.code from sys_role r",
            "RIGHT JOIN sys_user_role ur on r.id = ur.role_id",
            "where r.deleted = 0 and r.status = 0 and ur.deleted = 0 and ur.user_id = #{userId}"
    })
    List<RoleVo> getRoleListByUserId(Long userId);
}
