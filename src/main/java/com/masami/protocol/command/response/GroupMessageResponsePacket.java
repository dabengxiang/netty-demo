package com.masami.protocol.command.response;

import com.masami.nettyDemo.session.Session;
import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * @Author: gyc
 * @Date: 2019/9/2 11:53
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;


    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
