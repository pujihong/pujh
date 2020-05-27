package com.hewei.pujh.blog.service.impl;

import com.hewei.pujh.blog.entity.BlogLabel;
import com.hewei.pujh.blog.mapper.BlogLabelMapper;
import com.hewei.pujh.blog.service.IBlogLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.blog.vo.BlogLabelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@Service
public class BlogLabelServiceImpl extends ServiceImpl<BlogLabelMapper, BlogLabel> implements IBlogLabelService {

    @Resource
    private BlogLabelMapper labelMapper;
    @Override
    public List<BlogLabelVo> getUserBlogLabelList(Long userId) {
        return labelMapper.getUserBlogLabelList(userId);
    }
}
