package com.hewei.pujh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hewei.pujh.sys.entity.SysUser;
import com.hewei.pujh.sys.vo.UserVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

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
}
