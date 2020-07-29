package com.hewei.pujh.web.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.web.blog.entity.BlogLabel;
import com.hewei.pujh.web.blog.mapper.BlogArticleMapper;
import com.hewei.pujh.web.blog.mapper.BlogLabelMapper;
import com.hewei.pujh.web.blog.service.IBlogLabelService;
import com.hewei.pujh.web.blog.vo.BlogLabelVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    private static final Logger logger = LoggerFactory.getLogger(BlogLabelServiceImpl.class);

    @Resource
    private BlogLabelMapper labelMapper;
    @Resource
    private BlogArticleMapper articleMapper;

    @Override
    public List<BlogLabelVo> getUserAllBlogLabelList(Long userId) {
        return labelMapper.getUserAllBlogLabelList(userId);
    }

    @Override
    public IPage<BlogLabelVo> getUserBlogLabelList(Integer pageNum, Integer pageSize, String name, Long userId) {
        return labelMapper.getUserBlogLabelList(new Page<>(pageNum, pageSize), name, userId);
    }

    @Override
    public boolean saveBlogLabel(Long labelId, String name, Long userId) {
        BlogLabel label = new BlogLabel();
        label.setName(name);
        label.setUserId(userId);
        if (labelId == null) {
            return labelMapper.insert(label) == 1;
        } else {
            label.setId(labelId);
            return labelMapper.updateById(label) == 1;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBlogLabelById(Long labelId, Long userId) {
        BlogLabel label = new BlogLabel();
        label.setId(labelId);
        label.setDeleted(1);
        try {
            boolean result = labelMapper.updateById(label) == 1;
            if (result) {
                articleMapper.deleteUserBlogLabelByLabelId(labelId, userId);
            }
            return result;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("删除标签失败", e);
            return false;
        }
    }
}
