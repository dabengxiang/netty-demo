package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class SendtoUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要发送的用户和消息");
        String userId = scanner.next();
        String msg = scanner.nextLine();
        channel.writeAndFlush(new MessageRequestPacket(userId,msg));
    }

}
