package com.hewei.pujh.netty;

import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.netty.entity.RequestModel;
import com.hewei.pujh.utils.DateUtil;
import com.hewei.pujh.utils.FileUtil;
import com.hewei.pujh.web.base.entity.BaseAttachment;
import com.hewei.pujh.web.base.service.IBaseAttachmentService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static NettyServerHandler handler;

    @Value("${file.base.path}")
    private String fileBasePath;
    @Value("${server.host}")
    private String serverHost;

    @Autowired
    private IBaseAttachmentService attachmentService;

    @PostConstruct
    public void init() {
        handler = this;
    }


    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("连接客户端成功......");
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        RequestModel request = (RequestModel) msg;
        byte[] byteFile = request.getFile();
        String fileName = request.getFileName();
        Long attachmentId = null;
        if (byteFile != null) {
            String date = DateUtil.getCurrentDateStr();
            String path = File.separator + date;
            String baseVisitPath = "/" + date + "/" + fileName;
            String localPath = handler.fileBasePath + path;
            FileUtil.getFile(byteFile, localPath, request.getFileName());
            String accessPath = handler.serverHost + baseVisitPath;
            BaseAttachment attachment = new BaseAttachment();
            attachment.setAccessPath(accessPath);
            attachment.setLocalPath(localPath + File.separator + fileName);
            handler.attachmentService.save(attachment);
            attachmentId = attachment.getId();
        }
        ctx.writeAndFlush("你好呀");
        ctx.writeAndFlush(ResultModel.success());
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}