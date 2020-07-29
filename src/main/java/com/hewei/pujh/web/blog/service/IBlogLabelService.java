package com.hewei.pujh.web.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hewei.pujh.web.blog.entity.BlogLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.web.blog.vo.BlogLabelVo;

import java.util.List;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
public interface IBlogLabelService extends IService<BlogLabel> {

    List<BlogLabelVo> getUserAllBlogLabelList(Long userId);

    IPage<BlogLabelVo> getUserBlogLabelList(Integer pageNum, Integer pageSize, String name, Long userId);

    boolean saveBlogLabel(Long labelId, String name, Long userId);

    boolean deleteBlogLabelById(Long labelId,Long userId);
}
