package com.masami.protocol.command.response;

import com.masami.protocol.command.Command;
import com.masami.protocol.command.Packet;
import lombok.Data;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
@Data
public class LogoutResponsePacket extends Packet {

    private String reason;

    private boolean success;

    @Override
    public byte getCommand() {
        return Command.LOG_OUT_RESPONSE;
    }

    public LogoutResponsePacket(boolean success) {
        this.success = success;
    }

    public LogoutResponsePacket(boolean success,String reason) {
        this.reason = reason;
        this.success = success;
    }

    public static LogoutResponsePacket SUCCESS(){
        return  new LogoutResponsePacket(true);
    }


    public static LogoutResponsePacket FAIL(String reason){
        return  new LogoutResponsePacket(false,reason);
    }





}
