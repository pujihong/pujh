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
 * 博客文章表
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogArticle implements Serializable {

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
     * 标题
     */
    private String title;

    /**
     * 是否是markdown
     */
    private Integer boolMarkdown;

    /**
     * 是否发布
     */
    private Integer boolPublish;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容html格式
     */
    private String htmlContent;

    /**
     * 文章简介
     */
    private String description;

    /**
     * 标签id
     */
    private Long lableId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 浏览量
     */
    private Integer countView;

    /**
     * 评论数
     */
    private Integer countComment;


}
