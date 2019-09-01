package com.masami.nettyDemo.client.handler;

import com.masami.protocol.command.response.CreateGroupRespnsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupRespnsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRespnsePacket createGroupRespnsePacket) throws Exception {
        System.out.print("群创建成功，id 为[" + createGroupRespnsePacket.getGourpId() + "], ");
        System.out.println("群里面有：" + createGroupRespnsePacket.getUserNameList());
    }
}
