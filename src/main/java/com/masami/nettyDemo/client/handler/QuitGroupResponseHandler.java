package com.masami.nettyDemo.client.handler;

import com.masami.protocol.command.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket responsePacket) throws Exception {

        if(responsePacket.isSuccess()){
            System.out.println("退出群组成功");
        }else{
            System.out.println("退出群组失败，原因：" + responsePacket.getReason());

        }


    }
}
