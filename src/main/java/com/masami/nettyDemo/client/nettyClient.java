package com.masami.nettyDemo.client;

import com.masami.nettyDemo.client.console.ConsoleCommandManager;
import com.masami.nettyDemo.client.console.LoginConsoleCommand;
import com.masami.nettyDemo.client.handler.*;
import com.masami.nettyDemo.codec.PacketDecoder;
import com.masami.nettyDemo.codec.PacketEncoder;
import com.masami.nettyDemo.codec.Spliter;
import com.masami.nettyDemo.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author: gyc
 * @Date: 2019/8/23 11:50
 */
public class nettyClient {

    public static int MAX_RETRY = 3;

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new Spliter());
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageRepsponseHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                        channel.pipeline().addLast(new CreateGroupResponseHandler());
                        channel.pipeline().addLast(new LogoutReponseHandler());
                        channel.pipeline().addLast(new JoinGroupResponseHandler());
                        channel.pipeline().addLast(new ListGroupMemberResponseHandler());
                        channel.pipeline().addLast(new QuitGroupResponseHandler() );

                        channel.pipeline().addLast(new GroupMessageResponseHandler() );

                        startConsoleThread(channel);


                    }
                });

        connect(bootstrap, "127.0.0.1", 8000, 3);

    }

    /**
     * 重新连接
     * @param bootstrap
     * @param ip
     * @param port
     * @param retry
     * @return
     */
    public static ChannelFuture  connect(Bootstrap bootstrap , String ip ,int port ,int retry){
        ChannelFuture channelFuture = bootstrap.connect(ip, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接" + ip + ":" + port + "成功");
                    return ;
                }
                if(retry == 0 ){
                    System.out.println("连接" + ip + ":" + port + "失败");
                    return;
                }
                int delay = 1;  //失败后，1秒再连
                bootstrap.config().group().schedule(() -> connect(bootstrap, ip, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
        return channelFuture;

    }


    private static void startConsoleThread(Channel channel){

        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while(!Thread.interrupted()){
                if(SessionUtil.isLogin(channel)){
                    consoleCommandManager.exec(scanner,channel);
                }else{
                    loginConsoleCommand.exec(scanner,channel);
                    writeForReponse();
                }
            }

        }).start();
    }

    public static void writeForReponse(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
