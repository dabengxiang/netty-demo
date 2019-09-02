package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: gyc
 * @Date: 2019/9/2 14:12
 */
public class groupMessageConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要发送的群组id和信息");
        String groupId = scanner.next();
        String message = scanner.nextLine();

        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setToGroupId(groupId);
        groupMessageRequestPacket.setMessagge(message);
        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
