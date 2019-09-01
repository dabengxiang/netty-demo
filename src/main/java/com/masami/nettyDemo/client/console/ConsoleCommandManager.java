package com.masami.nettyDemo.client.console;

import com.masami.nettyDemo.session.Session;
import com.masami.nettyDemo.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private static Map<String , ConsoleCommand> commandMap = new HashMap<String,ConsoleCommand>();


    static {
        commandMap.put("sendToUser",new SendtoUserConsoleCommand());
        commandMap.put("createGroup",new CreateGroupConsoleCommand());
        commandMap.put("logout",new LogoutConsoleCommand());
    }


    @Override
    public void exec(Scanner scanner, Channel channel) {

        if(!SessionUtil.isLogin(channel)){
            System.out.println("用户还没登录请重新登录");
            return ;
        }

        System.out.println("请输入相应的命令：");
        String commandStr = scanner.nextLine();
        ConsoleCommand consoleCommand = commandMap.get(commandStr);
        if(consoleCommand!=null){
            consoleCommand.exec(scanner,channel);
        }else{
            System.out.println("输入的命令有误，请重新再输");
        }



    }
}
