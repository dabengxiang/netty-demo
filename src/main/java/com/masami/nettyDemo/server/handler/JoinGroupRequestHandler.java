package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.JoinGroupRequestPacket;
import com.masami.protocol.command.response.JoinGroupResponsePacket;
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
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();

        do{
            if(!SessionUtil.isLogin(ctx.channel())){
                responsePacket.setSuccess(false);
                responsePacket.setReason("还没有登录，请登录");
                System.out.println("还没有登录，请登录");
                break;
            }
            ChannelGroup channelGroup = SessionUtil.getChannelGroup(joinGroupRequestPacket.getGroupId());
            if(channelGroup==null){
                responsePacket.setSuccess(false);
                responsePacket.setReason("不存在该群组。。");
                System.out.println("不存在该群组。。");
                break;
            }

            channelGroup.add(ctx.channel());
            responsePacket.setSuccess(true);
            System.out.println("登录成功");


        }while (false);


        ctx.channel().writeAndFlush(responsePacket);

    }


    public static JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();



    public static JoinGroupRequestHandler getInstance (){
        return INSTANCE;
    }



}
