package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.LogoutRequestPacket;
import com.masami.protocol.command.response.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        ctx.writeAndFlush(LogoutResponsePacket.SUCCESS());
    }


    public static LogoutRequestHandler INSTANCE = new LogoutRequestHandler();



    public static LogoutRequestHandler getInstance (){
        return INSTANCE;
    }
}
