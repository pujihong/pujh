package com.hewei.pujh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hewei.pujh.blog.entity.BlogArticle;
import com.hewei.pujh.blog.vo.BlogArticleVo;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 博客文章表 Mapper 接口
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {


    @Select({
            "<script> ",
            "select ba.id,ba.create_time,ba.title,ba.bool_markdown,ba.bool_publish,ba.content,",
            "ba.html_content,ba.upload_article_url,ba.label_id,ba.user_id,ba.count_view,ba.count_comment,",
            "bl.`name` as labelName,su.username as userName",
            "from blog_article ba",
            "LEFT JOIN blog_label bl on ba.label_id = bl.id",
            "LEFT JOIN sys_user su on ba.user_id = su.id",
            "where ba.deleted = 0 and ba.user_id = #{userId}",
            "<if test='boolPublish != null'> and ba.bool_publish = #{boolPublish}</if>",
            "<if test='title != null and title != &apos;&apos;'> and ba.title like concat('%', #{title}, '%')</if>",
            "<if test='labelIds != null and labelIds.size > 0'> and ( ba.label_id in",
            "<foreach  collection = 'labelIds' item = 'id' index = 'index' open = '(' separator= ',' close = ')' >",
            "#{id}",
            "</foreach>",
            "<if test='containNoLabel'>",
            "or  ba.label_id is null or ba.label_id = '' ",
            "</if>",
            ")",
            "</if>",
            "<if test='startDate !=null and startDate !=&apos;&apos;'>",
            "and date_format(ba.create_time,'%Y-%m-%d') &gt;= date_format(#{startDate},'%Y-%m-%d')",
            "</if>",
            "<if test='endDate !=null and endDate != &apos;&apos;'>",
            "and date_format(ba.create_time,'%Y-%m-%d') &lt;= date_format(#{endDate},'%Y-%m-%d')",
            "</if>",
            "order by ba.create_time desc",
            "</script> "
    })
    IPage<BlogArticleVo> getUserBlogArticleList(@Param("page") Page<Object> page, @Param("boolPublish") Integer boolPublish,
                                                @Param("title") String title, @Param("labelIds") List<Long> labelIds,
                                                @Param("startDate") String startDate, @Param("endDate") String endDate,
                                                @Param("containNoLabel") boolean containNoLabel, @Param("userId") Long userId);

    @Select({
            "select ba.id,ba.create_time,ba.title,ba.bool_markdown,ba.bool_publish,ba.content,",
            "ba.html_content,ba.upload_article_url,ba.label_id,ba.user_id,ba.count_view,ba.count_comment,",
            "bl.name as labelName,su.username as userName",
            "from blog_article ba",
            "LEFT JOIN blog_label bl on ba.label_id = bl.id",
            "LEFT JOIN sys_user su on ba.user_id = su.id",
            "where ba.deleted = 0 and ba.id = #{articleId}",
    })
    @ResultType(BlogArticleVo.class)
    BlogArticleVo getBlogArticleById(Long articleId);

    @Update({
            "update blog_article set label_id = null where label_id = #{labelId} and user_id = #{userId}"
    })
    void deleteUserBlogLabelByLabelId(@Param("labelId") Long labelId, @Param("userId") Long userId);
}
