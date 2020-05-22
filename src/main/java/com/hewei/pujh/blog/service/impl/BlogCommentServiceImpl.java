package com.hewei.pujh.blog.service.impl;

import com.hewei.pujh.blog.entity.BlogComment;
import com.hewei.pujh.blog.mapper.BlogCommentMapper;
import com.hewei.pujh.blog.service.IBlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

}
