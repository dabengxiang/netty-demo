package com.masami.nettyDemo.codec;

import com.masami.protocol.command.Packet;
import com.masami.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: gyc
 * @Date: 2019/8/29 17:11
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List out) throws Exception {
        Packet packet = PacketCodeC.decode(byteBuf);
        out.add(packet);
    }
}
