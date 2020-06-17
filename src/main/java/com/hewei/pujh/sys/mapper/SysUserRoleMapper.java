package com.hewei.pujh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hewei.pujh.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户角色关联表表 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Insert({
            "<script> ",
            "INSERT INTO sys_user_role(create_user, user_id, role_id) values",
            "<foreach  collection = 'userRoleList' item = 'item'  separator= ','  >",
            "(#{item.createUser},#{item.userId},#{item.roleId})",
            "</foreach>",
            "</script> "
    })
    void batchInsert(@Param("userRoleList") List<SysUserRole> userRoleList);

    @Update({
            "<script> ",
            "update sys_user_role set deleted = 1, update_user=#{currentUserId} where deleted = 0 and user_id = #{userId} and role_id in",
            "<foreach  collection = 'removeArr' item = 'id' index = 'index' open = '(' separator= ',' close = ')' >",
            "#{id}",
            "</foreach>",
            "</script> "
    })
    void deleteBatchUserRole(@Param("removeArr") List<Long> removeArr, @Param("userId") Long userId, @Param("currentUserId") Long currentUserId);


    @Update({
            "update sys_user_role set deleted = 1, update_user=#{currentUserId} where deleted = 0 and user_id = #{userId}",
    })
    void deleteUserRole(@Param("userId") Long userId, @Param("currentUserId") Long currentUserId);
}
