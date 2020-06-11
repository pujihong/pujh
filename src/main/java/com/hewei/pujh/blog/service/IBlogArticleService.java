package com.hewei.pujh.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.blog.entity.BlogArticle;
import com.hewei.pujh.blog.vo.BlogArticleVo;

import java.util.List;

/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
public interface IBlogArticleService extends IService<BlogArticle> {

    Boolean saveBlogArticle(Long articleId, String title, Long labelId, String content, String htmlContent, Integer boolMarkdown, Integer boolPublish, Long userId);

    IPage<BlogArticleVo> getUserBlogArticleList(Integer pageNum, Integer pageSize, Integer boolPublish, String title, List<Long> labelIds, String startDate, String endDate, Long userId);

    BlogArticleVo getBlogArticleById(Long articleId);

    Boolean deleteBlogArticleById(Long articleId);

    void saveBlogArticlePublishById(Long articleId);
}
