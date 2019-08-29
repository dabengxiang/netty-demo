package com.masami.nettyDemo.server;

import com.masami.protocol.command.Packet;
import com.masami.protocol.command.PacketCodeC;
import com.masami.protocol.command.request.LoginRequestPacket;
import com.masami.protocol.command.request.MessageRequestPacket;
import com.masami.protocol.command.request.MessageResponsePacket;
import com.masami.protocol.command.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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

        }else if (packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + "，收到消息：" + messageRequestPacket.getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }

    }


    public boolean vaild (LoginRequestPacket loginRequestPacket){
        return true;
    }
}
