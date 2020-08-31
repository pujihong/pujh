package com.hewei.pujh.web.base.vo;

import lombok.Data;

@Data
public class AttachmentVo {
    private Integer id;
    /**
     * 本地路径
     */
    private String localPath;

    /**
     * 访问路径
     */
    private String accessPath;
}
