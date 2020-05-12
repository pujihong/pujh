package com.hewei.pujh.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author pujihong
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

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

    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单对应的url
     */
    private String url;

    /**
     * 0 navbar菜单
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * Level
     */
    private Integer level;

    /**
     * 已激活
     */
    private Integer status;


}
