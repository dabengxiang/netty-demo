package com.masami.protocol.command;

import lombok.Data;

/**
 * Date:2019/8/27
 * Author:gyc
 * Desc:
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }


}
