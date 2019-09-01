package com.masami.protocol.command.response;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }


}

