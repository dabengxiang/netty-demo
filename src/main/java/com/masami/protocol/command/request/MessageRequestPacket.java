package com.masami.protocol.command.request;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * @Author: gyc
 * @Date: 2019/8/29 11:06
 */
@Data
public class MessageRequestPacket extends Packet {

    private String userId;
    private String message;


    @Override
    public byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public MessageRequestPacket(String userId,String message) {
        this.userId = userId;
        this.message = message;
    }
}
