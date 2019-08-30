package com.masami.nettyDemo.server.handler;

import com.masami.nettyDemo.utils.LoginUtil;
import com.masami.protocol.command.request.LoginRequestPacket;
import com.masami.protocol.command.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: gyc
 * @Date: 2019/8/29 18:24
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        if(vaild(loginRequestPacket)){
            responsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println(new Date() + ": 登录成功!");
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


}
