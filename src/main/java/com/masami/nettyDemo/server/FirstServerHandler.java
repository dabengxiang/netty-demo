package com.masami.nettyDemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @Author: gyc
 * @Date: 2019/8/27 11:47
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(Charset.forName("utf-8")));
        ByteBuf toClientBytes = getByteBuf(ctx);
        ctx.channel().writeAndFlush(toClientBytes);
        super.channelRead(ctx, msg);

    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx){

        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "欢迎来到我的服务端".getBytes();
        return buffer.writeBytes(bytes);
    }
}
