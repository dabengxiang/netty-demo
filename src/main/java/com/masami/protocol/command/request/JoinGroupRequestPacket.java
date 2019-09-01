package com.masami.protocol.command.request;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
@Data
public class JoinGroupRequestPacket extends Packet {


    private String groupId;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }

    public JoinGroupRequestPacket(String groupId) {
        this.groupId = groupId;
    }
}
