package com.hewei.pujh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hewei.pujh.blog.entity.BlogLabel;
import com.hewei.pujh.blog.vo.LabelVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 分类 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
public interface BlogLabelMapper extends BaseMapper<BlogLabel> {

    @Select({
            "select id,name from blog_label where deleted = 0 and user_id = #{userId} order by sort"
    })
    @ResultType(LabelVo.class)
    List<LabelVo> getUserBlogLabelList(Long userId);
}
