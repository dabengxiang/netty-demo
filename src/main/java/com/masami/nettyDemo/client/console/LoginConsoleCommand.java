package com.masami.nettyDemo.client.console;

import com.masami.protocol.command.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入用户名登陆：");
        String userName = scanner.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setPassword("123456");
        channel.writeAndFlush(loginRequestPacket);
    }



}
