package com.masami.protocol.command.request;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

import java.util.List;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }


    public CreateGroupRequestPacket(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
