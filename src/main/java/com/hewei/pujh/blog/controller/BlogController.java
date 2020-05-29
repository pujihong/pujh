package com.hewei.pujh.blog.controller;

import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.blog.service.IBlogArticleService;
import com.hewei.pujh.blog.service.IBlogLabelService;
import com.hewei.pujh.enums.BoolEnum;
import com.hewei.pujh.sys.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "博客")
public class BlogController {
    @Autowired
    private IBlogLabelService getUserBlogLabel;
    @Autowired
    private IBlogArticleService articleService;

    @PostMapping(path = "/getUserBlogLabelList")
    @ApiOperation(value = "查询用户博客标签列表")
    public ResultModel getUserBlogLabelList(@ApiIgnore @CurrentUser UserVo user) {
        return ResultModel.success(getUserBlogLabel.getUserBlogLabelList(user.getId()));
    }

    @PostMapping(path = "/getUserBlogArticleList")
    @ApiOperation(value = "查询用户博客列表")
    public ResultModel getUserBlogArticleList(@ApiIgnore @CurrentUser UserVo user,
                                              @RequestParam(required = false) Integer boolPublish,
                                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        if (boolPublish != null && BoolEnum.toEnum(boolPublish) == null) {
            return ResultModel.error(ResultModel.WRONG_PARAMS_ERROR);
        }
        return ResultModel.success(articleService.getUserBlogArticleList(pageNum, pageSize, boolPublish, user.getId()));
    }

    @PostMapping(path = "/saveBlogArticle")
    @ApiOperation(value = "保存博客")
    public ResultModel saveBlogArticle(@ApiIgnore @CurrentUser UserVo user,
                                       @RequestParam Long articleId,
                                       @RequestParam String title,
                                       @RequestParam(required = false) Long labelId,
                                       @RequestParam String content,
                                       @RequestParam(required = false) String htmlContent,
                                       @RequestParam(required = false, defaultValue = "1") Integer boolMarkdown,
                                       @RequestParam(required = false, defaultValue = "0") Integer boolPublish) {
        if (StringUtils.isAnyBlank(title, content)) {
            return ResultModel.error(ResultModel.WRONG_PARAMS_ERROR);
        }
        if (boolMarkdown == 1 && StringUtils.isBlank(htmlContent)) {
            return ResultModel.error(ResultModel.WRONG_PARAMS_ERROR);
        }
        boolean result = articleService.saveBlogArticle(articleId, title, labelId, content, htmlContent, boolMarkdown, boolPublish, user.getId());
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ResultModel.OP_FAILED_ERROR);
        }
    }

    @PostMapping(path = "/getBlogArticleById")
    @ApiOperation(value = "查询博客详情")
    public ResultModel getBlogArticleById(@RequestParam Long articleId) {
        return ResultModel.success(articleService.getBlogArticleById(articleId));
    }

    @PostMapping(path = "/deleteBlogArticleById")
    @ApiOperation(value = "删除博客信息")
    public ResultModel deleteBlogArticleById(@RequestParam Long articleId) {
        boolean result = articleService.deleteBlogArticleById(articleId);
        if (result) {
            return ResultModel.success();
        } else {
            return ResultModel.error(ResultModel.OP_FAILED_ERROR);
        }
    }

}
