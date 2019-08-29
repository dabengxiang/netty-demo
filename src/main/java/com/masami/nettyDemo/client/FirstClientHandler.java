package com.masami.nettyDemo.client;

import com.masami.nettyDemo.utils.LoginUtil;
import com.masami.protocol.command.Packet;
import com.masami.protocol.command.PacketCodeC;
import com.masami.protocol.command.request.LoginRequestPacket;
import com.masami.protocol.command.request.MessageResponsePacket;
import com.masami.protocol.command.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author: gyc
 * @Date: 2019/8/27 11:17
 */
public class FirstClientHandler  extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf byteBuf = getByteBuf(ctx);

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId("0xabc");
        loginRequestPacket.setUserName("张三");
        loginRequestPacket.setPassword("123456");
        ByteBuf byteBuf = PacketCodeC.encode(ctx.alloc(),loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.decode(byteBuf);
        if(packet  instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + "登录成功");
                LoginUtil.markAsLogin(ctx.channel());  //给attr标志，标志已经登陆了

            }else{
                System.out.println(new Date() + "登录失败，原因是：" + loginResponsePacket.getReason());

            }

        }
        else if(packet  instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());

        }
        super.channelRead(ctx, msg);
    }







}
