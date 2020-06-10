package com.hewei.pujh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hewei.pujh.blog.entity.BlogLabel;
import com.hewei.pujh.blog.vo.BlogLabelVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

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
            "select id,name from blog_label where deleted = 0 and user_id = #{userId} order by name"
    })
    @ResultType(BlogLabelVo.class)
    List<BlogLabelVo> getUserAllBlogLabelList(Long userId);

    @Select({
            "<script> ",
            "select id,name from blog_label where deleted = 0 and user_id = #{userId}",
            "<if test='name != null and name != &apos;&apos;'> and `name` like concat('%', #{name}, '%')</if>",
            "order by name",
            "</script> "
    })
    @ResultType(BlogLabelVo.class)
    IPage<BlogLabelVo> getUserBlogLabelList(@Param("page") Page<Object> objectPage, @Param("name") String name, @Param("userId") Long userId);
}
