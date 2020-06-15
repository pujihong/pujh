package com.hewei.pujh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.vo.UserVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-01-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select({
            "select id,username from sys_user where deleted = 0 and id = #{userId}"
    })
    @ResultType(UserVo.class)
    UserVo getUserVoById(Long userId);

    @Select({
            "select id,create_time,update_time,username,`password` from sys_user where deleted = 0"
    })
    @ResultType(SysUser.class)
    SysUser getUserByUsername(String username);

    @Select({
            "<script> ",
            "select u.id,u.username,ur.role_id,r.`name` as roleName",
            "from sys_user u",
            "LEFT JOIN sys_user_role ur on u.id = ur.user_id",
            "LEFT JOIN sys_role r on ur.role_id = r.id",
            "where u.deleted = 0 and u.id != 0 ",
            "<if test='name != null and name != &apos;&apos;'> and u.username like concat('%', #{name}, '%')</if>",
            "<if test='roleIds != null and roleIds.size > 0'>and ur.role_id in",
            "<foreach  collection = 'roleIds' item = 'id' index = 'index' open = '(' separator= ',' close = ')' >",
            "#{id}",
            "</foreach>",
            "</if>",
            "order by u.create_time desc",
            "</script> "
    })
    IPage<UserVo> getUserList(@Param("objectPage") Page<Object> objectPage, @Param("name") String name, @Param("roleIds") List<Long> roleIds);
}
