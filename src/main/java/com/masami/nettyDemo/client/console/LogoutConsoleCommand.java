package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登出命令
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class LogoutConsoleCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
        writeForReponse();
    }


    public static void writeForReponse(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
