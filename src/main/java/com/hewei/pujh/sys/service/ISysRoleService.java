package com.hewei.pujh.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hewei.pujh.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.sys.vo.RoleVo;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
public interface ISysRoleService extends IService<SysRole> {

    List<RoleVo> getAllRoleList();

    IPage<RoleVo> getRoleList(Integer pageNum, Integer pageSize, String name, Integer status);

    boolean saveRole(Long roleId, String name, String code, Long userId);

    boolean saveRoleMenu(List<Long> menuIds, Long roleId, Long userId);
}
