package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.session.Session;
import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.ListGroupMemberRequestPacket;
import com.masami.protocol.command.response.ListGroupMemberResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket listGroupMemberRequestPacket) throws Exception {

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(listGroupMemberRequestPacket.getGroupId());

        ListGroupMemberResponsePacket responsePacket = new ListGroupMemberResponsePacket();

        do{
            if(channelGroup == null){
                responsePacket.setSuccess(false);
                responsePacket.setReason("不存在该群组！");
                break;
            }

            List<Session> sessionList = new ArrayList<>();
            for (Channel channel : channelGroup) {
                Session session = SessionUtil.getSession(channel);
                sessionList.add(session);
            }

            responsePacket.setSessonList(sessionList);
            responsePacket.setGroupId(listGroupMemberRequestPacket.getGroupId());
            responsePacket.setSuccess(true);

        }while(false);


        ctx.channel().writeAndFlush(responsePacket);
    }
}
