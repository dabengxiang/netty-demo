package com.masami.nettyDemo.server;

import com.masami.protocol.command.Packet;
import com.masami.protocol.command.PacketCodeC;
import com.masami.protocol.command.request.LoginRequestPacket;
import com.masami.protocol.command.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: gyc
 * @Date: 2019/8/27 11:47
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket responsePacket = new LoginResponsePacket();


            if(vaild(loginRequestPacket)){
                responsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            }else{
                responsePacket.setReason("账号密码校验失败");
                responsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            ByteBuf sendByteBuf = PacketCodeC.encode(ctx.alloc(),responsePacket);

            ctx.channel().writeAndFlush(sendByteBuf);

        }

    }


    public boolean vaild (LoginRequestPacket loginRequestPacket){
        return true;
    }
}
