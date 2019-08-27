package com.masami.netty;

import com.masami.nettyDemo.serialize.Serializer;
import com.masami.nettyDemo.serialize.impl.JSONSerializer;
import com.masami.protocol.command.LoginRequestPacket;
import com.masami.protocol.command.Packet;
import com.masami.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
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
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
