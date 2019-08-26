package com.masami.nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author: gyc
 * @Date: 2019/8/23 11:26
 */
public class nettyServer {


    public static void main(String[] args) {

        ServerBootstrap serverBoostrap = new ServerBootstrap();


        NioEventLoopGroup boss = new NioEventLoopGroup();  //boos对应，IOServer.java中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup worker = new NioEventLoopGroup(); //worker对应 IOClient.java中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理


        serverBoostrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringDecoder());
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                }).bind(8000);


    }


}
