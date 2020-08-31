package com.hewei.pujh.web.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hewei.pujh.web.base.entity.BaseAttachment;
import com.hewei.pujh.web.base.mapper.BaseAttachmentMapper;
import com.hewei.pujh.web.base.service.IBaseAttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author pujihong
 * @since 2020-07-30
 */
@Service
public class BaseAttachmentServiceImpl extends ServiceImpl<BaseAttachmentMapper, BaseAttachment> implements IBaseAttachmentService {

    @Resource
    private BaseAttachmentMapper attachmentMapper;
}
