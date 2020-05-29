package com.hewei.pujh.blog.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogArticleVo {

    private Long id;
    private LocalDateTime createTime;
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
    private Long labelId;

    private String labelName;

    /**
     * 用户id
     */
    private Long userId;
    private String userName;

    /**
     * 浏览量
     */
    private Integer countView;

    /**
     * 评论数
     */
    private Integer countComment;
}
