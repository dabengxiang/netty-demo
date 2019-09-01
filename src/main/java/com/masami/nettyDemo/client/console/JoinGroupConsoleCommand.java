package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要加入的群组id:");
        String gourpId = scanner.nextLine();
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket(gourpId);
        channel.writeAndFlush(joinGroupRequestPacket);

    }
}
