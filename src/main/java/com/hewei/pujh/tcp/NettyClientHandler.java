package com.hewei.pujh.tcp;

import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.tcp.entity.RequestModel;
import com.hewei.pujh.utils.DateUtil;
import com.hewei.pujh.utils.FileUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        RequestModel model = new RequestModel();
        model.setLineId(1);
        model.setLine("1号线");
        model.setDate(DateUtil.getCurrentDateTimeStr("yyyy-MM-dd hh:mm:ss"));
        model.setEquipmentName("小车");
        model.setInjuredName("轨枕伤损");
        model.setLevel(1);
        model.setPosition(5000);
        File file = new File("F:\\复习脑图.jpg");
        model.setFile(FileUtil.File2byte(file));
        model.setFileName(file.getName());
        ctx.write(model);
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof ResultModel) {
            ResultModel result = (ResultModel) msg;
            log.info(result.getCode() + "");
        } else {
            System.out.println(msg.toString());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
