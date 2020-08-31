package com.hewei.pujh.web.base.controller;

import com.hewei.pujh.annotation.CurrentUser;
import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.enums.ErrorCodeEnum;
import com.hewei.pujh.utils.DateUtil;
import com.hewei.pujh.utils.FileUtil;
import com.hewei.pujh.web.base.entity.BaseAttachment;
import com.hewei.pujh.web.base.service.IBaseAttachmentService;
import com.hewei.pujh.web.sys.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author pujihong
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/base/attachment")
@Slf4j
@Api(tags = "附件接口")
public class BaseAttachmentController {

    @Value("${file.base.path}")
    private String fileBasePath;
    @Value("${server.host}")
    String serverHost;

    @Autowired
    private IBaseAttachmentService attachmentService;

    @PostMapping("/upload")
    @ApiOperation(value = "单文件上传")
    public ResultModel uploadFile(@RequestParam("file") MultipartFile file, @ApiIgnore @CurrentUser UserVo user) {
        if (file.isEmpty()) {
            return ResultModel.error("没有文件！");
        }
        String fileName = file.getOriginalFilename();
    /*    assert fileName != null;
        int pos = fileName.lastIndexOf(".");
        String ext = "";
        if (pos >= 0) {
            ext = fileName.substring(pos);
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + ext;*/
        String date = DateUtil.getCurrentDateStr();
        String path = File.separator + date + File.separator + fileName;
        String baseVisitPath = "/" + date + "/" + fileName;
        String localPath = fileBasePath + path;
        String accessPath = serverHost + baseVisitPath;
        try {
            FileUtil.upload(file, fileBasePath, date, fileName);
            BaseAttachment attachment = new BaseAttachment();
            attachment.setCreateUser(user.getId());
            attachment.setAccessPath(accessPath);
            attachment.setLocalPath(localPath);
            if (attachmentService.save(attachment)) {
                return ResultModel.success(attachment.getId());
            } else {
                return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
            }
        } catch (IOException e) {
            log.error("上传文件失败", e);
            return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
        }
    }

    @PostMapping("/multiUpload")
    @ApiOperation(value = "多文件上传")
    public ResultModel multiUpload(@RequestParam("files") MultipartFile[] files, @ApiIgnore @CurrentUser UserVo user) {
        if (files == null || files.length == 0) {
            return ResultModel.error("没有文件！");
        }
        MultipartFile file;
        for (int i = 0; i < files.length; i++) {
            file = files[i];
            if (file.isEmpty()) {
                return ResultModel.error("第" + (i + 1) + "个文件失败！");
            }
        }
        String date = DateUtil.getCurrentDateStr();
        Long userId = user.getId();
        List<BaseAttachment> attaList = new ArrayList<>();
        BaseAttachment attachment;
        try {
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                String path = File.separator + date + File.separator + fileName;
                String baseVisitPath = "/" + date + "/" + fileName;
                String localPath = fileBasePath + path;
                String accessPath = serverHost + baseVisitPath;
                FileUtil.upload(multipartFile, fileBasePath, date, fileName);
                attachment = new BaseAttachment();
                attachment.setCreateUser(userId);
                attachment.setAccessPath(accessPath);
                attachment.setLocalPath(localPath);
                attaList.add(attachment);
            }
            if (attachmentService.saveBatch(attaList)) {
                List<Long> idList = new ArrayList<>();
                for (BaseAttachment baseAttachment : attaList) {
                    idList.add(baseAttachment.getId());
                }
                return ResultModel.success(idList);
            } else {
                return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
            }
        } catch (IOException e) {
            log.error("上传文件失败", e);
            return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
        }
    }

}
