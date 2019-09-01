package com.masami.nettyDemo.client.handler;

import com.masami.protocol.command.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if(joinGroupResponsePacket.isSuccess()){
            System.out.println("加入成功");
        }else{
            System.out.println("加入失败，原因：" + joinGroupResponsePacket.getReason());
        }

    }
}
