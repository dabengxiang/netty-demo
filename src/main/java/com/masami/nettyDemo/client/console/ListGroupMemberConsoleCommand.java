package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.ListGroupMemberRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Date:2019/9/1
 * Author:gyc
 * Desc:
 */
public class ListGroupMemberConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("请输入要查看群组的id:");
        String groupId = scanner.nextLine();
        ListGroupMemberRequestPacket requestPacket = new ListGroupMemberRequestPacket();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);


    }
}
