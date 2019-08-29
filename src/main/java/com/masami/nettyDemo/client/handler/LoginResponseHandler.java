package com.masami.nettyDemo.client.handler;

import com.masami.nettyDemo.utils.LoginUtil;
import com.masami.protocol.command.request.LoginRequestPacket;
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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId("0xabc");
        loginRequestPacket.setUserName("张三");
        loginRequestPacket.setPassword("123456");
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println(new Date() + "登录成功");
            LoginUtil.markAsLogin(ctx.channel());  //给attr标志，标志已经登陆了

        }else{
            System.out.println(new Date() + "登录失败，原因是：" + loginResponsePacket.getReason());

        }
    }

}

