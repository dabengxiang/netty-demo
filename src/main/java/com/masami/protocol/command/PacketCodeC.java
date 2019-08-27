package com.masami.protocol.command;

import com.masami.nettyDemo.serialize.Serializer;
import com.masami.nettyDemo.serialize.impl.JSONSerializer;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Date:2019/8/27
 * Author:gyc
 * Desc:
 */
public class PacketCodeC {


    private static final int MAGIC_NUMBER = 0x12345678;

    private static Map<Byte,Serializer> serializerMap = new HashMap();


    private static Map<Byte,Class<? extends Packet>> commandMap = new HashMap();

    static {
        JSONSerializer jsonSerializer = new JSONSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlogrithm(),jsonSerializer);

        commandMap.put(Command.LOGIN_REQUEST,LoginRequestPacket.class);

    }


    public ByteBuf encode(Packet packet){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        Serializer defaultSerializer = Serializer.DEFAULT;
        byte[] data = defaultSerializer.serialize(packet);
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(defaultSerializer.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){

        //跳过魔数
        byteBuf.skipBytes(4);
        //跳过版本
        byteBuf.skipBytes(1);
        //读取序列化类型
        byte serializerType = byteBuf.readByte();
        //读取命令
        byte command = byteBuf.readByte();
        //读取长度
        int dataLength = byteBuf.readInt();
        //读取数据
        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packetClz = commandMap.get(command);

        Serializer serializer = serializerMap.get(serializerType);

        if (packetClz != null && serializer != null) {
            return serializer.deSerialize(packetClz, bytes);
        }

        return null;


    }


}