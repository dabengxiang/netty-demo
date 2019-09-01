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
public class ListGroupMemberRequestPacket extends Packet {

    private String groupId;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBER_REQUEST;
    }
}
