package com.masami.nettyDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

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
                .handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                });
        bootstrap.attr(AttributeKey.newInstance("clientName"), "nettyClient");
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);
        connect(bootstrap, "127.0.0.1", 8886, 3);




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


}
