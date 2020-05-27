package com.hewei.pujh.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.blog.entity.BlogArticle;
import com.hewei.pujh.blog.vo.BlogArticleVo;

/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
public interface IBlogArticleService extends IService<BlogArticle> {

    Boolean saveBlogArticle(String title, Long labelId, String content, String htmlContent, Integer boolMarkdown, Integer boolPublish, Long userId);

    IPage<BlogArticleVo> getUserBlogArticleList(Integer pageNum, Integer pageSize,Integer boolPublish, Long userId);
}
