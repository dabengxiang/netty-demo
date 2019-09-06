package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.session.Session;
import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.MessageRequestPacket;
import com.masami.protocol.command.response.MessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: gyc
 * @Date: 2019/8/29 18:27
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + "，收到消息：" + messageRequestPacket.getMessage());

        Session session = SessionUtil.getSession(ctx.channel());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFormUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        Channel channel = SessionUtil.getChannel(messageRequestPacket.getUserId());

        if(channel != null && SessionUtil.isLogin(channel)){
            channel.writeAndFlush(messageResponsePacket);
        }else{
             System.out.println("该用户还没有上线！");
        }
    }


    public static MessageRequestHandler INSTANCE = new MessageRequestHandler();



    public static MessageRequestHandler getInstance (){
        return INSTANCE;
    }
}
