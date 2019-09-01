package com.masami.protocol.command.response;

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
public class CreateGroupRespnsePacket extends Packet{


    private boolean success;

    private String gourpId;

    private List<String> userNameList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
