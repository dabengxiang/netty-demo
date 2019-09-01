package com.masami.nettyDemo.client.handler;

import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.response.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class LogoutReponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        System.out.println("登出成功！！");
        SessionUtil.unbindSession(ctx.channel());
    }
}
