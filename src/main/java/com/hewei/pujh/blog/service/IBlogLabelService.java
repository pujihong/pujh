package com.hewei.pujh.blog.service;

import com.hewei.pujh.blog.entity.BlogLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hewei.pujh.blog.vo.LabelVo;

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

    List<LabelVo> getUserBlogLabel(Long userId);
}
