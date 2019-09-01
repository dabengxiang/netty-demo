package com.masami.protocol.command.request;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public byte getCommand() {
        return Command.LOG_OUT_REQUEST;
    }
}
