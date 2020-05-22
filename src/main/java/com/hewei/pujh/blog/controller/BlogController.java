package com.hewei.pujh.blog.controller;


import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.blog.service.IBlogLabelService;
import com.hewei.pujh.sys.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pujihong
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogLabelService getUserBlogLabel;

    @PostMapping(path = "/getUserBlogLabel")
    @ApiOperation(value = "查询用户博客标签")
    public ResultModel getUserBlogLabel(@ApiIgnore @CurrentUser UserVo user) {
        return ResultModel.success(getUserBlogLabel.getUserBlogLabel(user.getId()));
    }
}
