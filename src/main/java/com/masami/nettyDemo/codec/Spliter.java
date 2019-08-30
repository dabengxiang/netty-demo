package com.masami.nettyDemo.codec;

import com.masami.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: gyc
 * @Date: 2019/8/30 9:26
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    public Spliter() {
        super(Integer.MAX_VALUE, 7, 4);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
