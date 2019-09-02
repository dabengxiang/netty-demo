package com.masami.protocol.command.request;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * @Author: gyc
 * @Date: 2019/9/2 11:51
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String messagge;

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
