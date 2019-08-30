package com.masami.nettyDemo.client.handler;

import com.masami.protocol.command.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: gyc
 * @Date: 2019/8/29 18:23
 */
public class MessageRepsponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + ",收到服务端的数据：" + messageResponsePacket.getMessage());
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端被关闭");
    }

}
