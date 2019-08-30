package com.masami.nettyDemo.client.handler;

import com.masami.nettyDemo.session.Session;
import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: gyc
 * @Date: 2019/8/29 18:18
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println(new Date() + "登录成功" + ",userId:"+loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(loginResponsePacket.getUserId(),loginResponsePacket.getUserName()),ctx.channel());

        }else{
            System.out.println(new Date() + "登录失败，原因是：" + loginResponsePacket.getReason());

        }
    }


}

