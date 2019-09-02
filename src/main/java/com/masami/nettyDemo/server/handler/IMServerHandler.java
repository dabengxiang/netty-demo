package com.masami.nettyDemo.server.handler;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gyc
 * @Date: 2019/9/2 14:29
 */
public class IMServerHandler extends SimpleChannelInboundHandler<Packet> {


    private static  Map<Byte,SimpleChannelInboundHandler> handlerMap = new HashMap<>();

    static {

        handlerMap.put(Command.LOGIN_REQUEST, LoginRequestHandler.getInstance());
        handlerMap.put(Command.LOG_OUT_REQUEST, LogoutRequestHandler.getInstance());
        handlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.getInstance());
        handlerMap.put(Command.QUIT_GROUP_REQUEST,QuitGroupRequestHandler.getInstance());
        handlerMap.put(Command.LIST_GROUP_MEMBER_REQUEST, ListGroupMemberRequestHandler.getInstance());
        handlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.getInstance());
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.getInstance());


    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        SimpleChannelInboundHandler simpleChannelInboundHandler = handlerMap.get(packet.getCommand());
        simpleChannelInboundHandler.channelRead(channelHandlerContext,packet);
    }


    public static IMServerHandler INSTANCE = new IMServerHandler();



    public static IMServerHandler getInstance (){
        return INSTANCE;
    }
}
