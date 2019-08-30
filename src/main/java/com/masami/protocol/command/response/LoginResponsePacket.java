package com.masami.protocol.command.response;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * Date:2019/8/27
 * Author:gyc
 * Desc:
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;


    private String userName;


    private boolean success;

    private String reason;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
