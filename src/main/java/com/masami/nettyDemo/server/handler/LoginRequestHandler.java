package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.session.Session;
import com.masami.nettyDemo.utils.SessionUtil;
import com.masami.protocol.command.request.LoginRequestPacket;
import com.masami.protocol.command.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: gyc
 * @Date: 2019/8/29 18:24
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        if(vaild(loginRequestPacket)){
            System.out.println(new Date() + ": 登录成功!");
            String userId = randomUserId();
            SessionUtil.bindSession(new Session(userId,loginRequestPacket.getUserName()),ctx.channel());
            responsePacket.setSuccess(true);
            responsePacket.setUserId(userId);
            responsePacket.setUserName(loginRequestPacket.getUserName());


        }else{
            responsePacket.setReason("账号密码校验失败");
            responsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }

    public boolean vaild (LoginRequestPacket loginRequestPacket){
        return true;
    }

    private String randomUserId(){
       return UUID.randomUUID().toString().split("-")[0];
    }
}
