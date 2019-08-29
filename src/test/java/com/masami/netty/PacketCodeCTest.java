package com.masami.netty;

import com.masami.nettyDemo.serialize.Serializer;
import com.masami.nettyDemo.serialize.impl.JSONSerializer;
import com.masami.protocol.command.PacketCodeC;
import com.masami.protocol.command.request.LoginRequestPacket;
import org.junit.Test;


public class PacketCodeCTest {
    @Test
    public void encode() {

        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUserName("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = new PacketCodeC();
//        ByteBuf byteBuf = packetCodeC.encode(ByteBufAllocator.DEFAULT, loginRequestPacket);
//        Packet decodedPacket = packetCodeC.decode(byteBuf);

//        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
