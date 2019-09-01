package com.masami.nettyDemo.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public interface ConsoleCommand {

    public void exec(Scanner scanner, Channel channel);
}
