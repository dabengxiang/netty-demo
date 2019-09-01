package com.masami.protocol.command.response;

import com.masami.nettyDemo.session.Session;
import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

import java.util.List;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
@Data
public class ListGroupMemberResponsePacket extends Packet {

    private String groupId;


    private List<Session> sessonList;

    private boolean success;

    private String reason;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBER_RESPONSE;
    }
}
