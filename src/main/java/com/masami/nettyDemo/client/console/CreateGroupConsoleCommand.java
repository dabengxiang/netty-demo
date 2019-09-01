package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class CreateGroupConsoleCommand implements  ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIdGroup = scanner.nextLine();
        String[] userIds = userIdGroup.split(",");
        CreateGroupRequestPacket createGroupPacket = new CreateGroupRequestPacket(Arrays.asList(userIds));
        channel.writeAndFlush(createGroupPacket);
    }
}
