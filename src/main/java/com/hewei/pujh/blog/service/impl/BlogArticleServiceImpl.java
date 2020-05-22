package com.hewei.pujh.blog.service.impl;

import com.hewei.pujh.blog.entity.BlogArticle;
import com.hewei.pujh.blog.mapper.BlogArticleMapper;
import com.hewei.pujh.blog.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
