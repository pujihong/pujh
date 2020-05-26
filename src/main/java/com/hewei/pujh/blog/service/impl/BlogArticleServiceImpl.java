package com.hewei.pujh.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.blog.entity.BlogArticle;
import com.hewei.pujh.blog.mapper.BlogArticleMapper;
import com.hewei.pujh.blog.service.IBlogArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {
    @Resource
    private BlogArticleMapper articleMapper;

    @Override
    public boolean saveBlogArticle(String title, Long labelId, String content,String htmlContent, Integer boolMarkdown, Integer boolPublish, Long userId) {
        BlogArticle article = new BlogArticle();
        article.setBoolMarkdown(boolMarkdown);
        article.setBoolPublish(boolPublish);
        article.setTitle(title);
        article.setLableId(labelId);
        article.setContent(content);
        article.setHtmlContent(htmlContent);
        article.setUserId(userId);
        return articleMapper.insert(article) == 1;
    }
}
