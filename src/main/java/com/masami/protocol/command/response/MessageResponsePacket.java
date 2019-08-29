package com.masami.protocol.command.response;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * @Author: gyc
 * @Date: 2019/8/29 11:26
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;


    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
