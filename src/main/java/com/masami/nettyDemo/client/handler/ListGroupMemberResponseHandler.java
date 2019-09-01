package com.masami.nettyDemo.client.handler;

import com.masami.nettyDemo.session.Session;
import com.masami.protocol.command.response.ListGroupMemberResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMemberResponsePacket listGroupMemberResponsePacket) throws Exception {

        if(listGroupMemberResponsePacket.isSuccess()){
            System.out.println("群组人员：");
            List<Session> sessonList = listGroupMemberResponsePacket.getSessonList();
            for (Session session : sessonList) {
                System.out.print(session.getUserName() + ",");

            }
            System.out.print("");

        }else{
            System.out.println("获取失败，原因：" + listGroupMemberResponsePacket.getReason());
        }

    }
}
