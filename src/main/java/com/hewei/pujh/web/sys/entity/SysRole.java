package com.hewei.pujh.web.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long createUser;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    private Long updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private Integer deleted;

    /**
     * 登录密码
     */
    private String name;

    /**
     * 已激活
     */
    private Integer status;

    /**
     * 简称
     */
    private String code;


}
