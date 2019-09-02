package com.masami.nettyDemo.client.handler;

import com.masami.protocol.command.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: gyc
 * @Date: 2019/9/2 14:09
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        System.out.println("收到来自" + groupMessageResponsePacket.getFromGroupId() + "-用户" + groupMessageResponsePacket.getFromUser().getUserId() +":" + groupMessageResponsePacket.getMessage());
    }
}
