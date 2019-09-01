package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class QuitGroupConsoleCommand implements  ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入要退出的群组id");
        String groupId = scanner.nextLine();
        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
