package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.QuitGroupRequestPacket;
import com.masami.protocol.command.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {

        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if(channelGroup==null){
            responsePacket.setSuccess(false);
            responsePacket.setReason("不存在该群组");
        }else{

            channelGroup.remove(ctx.channel());
            responsePacket.setSuccess(true);

        }

        ctx.channel().writeAndFlush(responsePacket);
    }


    public static QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();



    public static QuitGroupRequestHandler getInstance (){
        return INSTANCE;
    }
}
