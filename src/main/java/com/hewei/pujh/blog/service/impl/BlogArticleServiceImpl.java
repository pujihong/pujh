package com.hewei.pujh.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.blog.entity.BlogArticle;
import com.hewei.pujh.blog.mapper.BlogArticleMapper;
import com.hewei.pujh.blog.service.IBlogArticleService;
import com.hewei.pujh.blog.vo.BlogArticleVo;
import com.hewei.pujh.enums.BoolEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public Boolean saveBlogArticle(Long articleId, String title, Long labelId, String content, String htmlContent, Integer boolMarkdown, Integer boolPublish, Long userId) {
        BlogArticle article = new BlogArticle();
        article.setBoolMarkdown(boolMarkdown);
        article.setBoolPublish(boolPublish);
        article.setTitle(title);
        article.setLabelId(labelId);
        article.setContent(content);
        article.setHtmlContent(htmlContent);
        article.setUserId(userId);
        if (articleId != null) {
            article.setId(articleId);
            return articleMapper.updateById(article) == 1;
        } else {
            return articleMapper.insert(article) == 1;
        }
    }

    @Override
    public IPage<BlogArticleVo> getUserBlogArticleList(Integer pageNum, Integer pageSize, Integer boolPublish, String title, List<Long> labelIds, String startDate, String endDate, Long userId) {
        boolean containNoLabel = false;
        if(labelIds != null && labelIds.size() > 0) {
            containNoLabel = labelIds.contains(-1L);
        }
        return articleMapper.getUserBlogArticleList(new Page<>(pageNum, pageSize), boolPublish, title, labelIds, startDate, endDate, containNoLabel, userId);
    }

    @Override
    public BlogArticleVo getBlogArticleById(Long articleId) {
        return articleMapper.getBlogArticleById(articleId);
    }

    @Override
    public Boolean deleteBlogArticleById(Long articleId) {
        BlogArticle article = new BlogArticle();
        article.setId(articleId);
        article.setDeleted(1);
        return articleMapper.updateById(article) == 1;
    }

    @Override
    public void saveBlogArticlePublishById(Long articleId) {
        BlogArticle article = new BlogArticle();
        article.setId(articleId);
        article.setBoolPublish(BoolEnum.YES.getValue());
        articleMapper.updateById(article);
    }
}
