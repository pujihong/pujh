package com.hewei.pujh.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客评论表
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Integer deleted;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 父评论id
     */
    private Long pid;

    /**
     * 点赞数
     */
    private Integer countLike;

    /**
     * 评论内容
     */
    private String content;


}
