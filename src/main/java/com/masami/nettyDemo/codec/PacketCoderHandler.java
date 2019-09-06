package com.masami.nettyDemo.codec;

import com.masami.protocol.command.Packet;
import com.masami.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @Author: gyc
 * @Date: 2019/9/3 14:35
 */
@ChannelHandler.Sharable
public class PacketCoderHandler extends MessageToMessageCodec<ByteBuf, Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> list) throws Exception {
        list.add(PacketCodeC.encode(ctx.alloc().ioBuffer(),packet));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Packet packet = PacketCodeC.decode(byteBuf);
        list.add(packet);
    }

    public static PacketCoderHandler INSTANCE = new PacketCoderHandler();



    public static PacketCoderHandler getInstance (){
        return INSTANCE;
    }
}
