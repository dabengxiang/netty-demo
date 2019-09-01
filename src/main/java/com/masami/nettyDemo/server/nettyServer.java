package com.masami.nettyDemo.server;

import com.masami.nettyDemo.codec.PacketDecoder;
import com.masami.nettyDemo.codec.PacketEncoder;
import com.masami.nettyDemo.codec.Spliter;
import com.masami.nettyDemo.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author: gyc
 * @Date: 2019/8/23 11:26
 */
public class nettyServer {


    public static void main(String[] args) {

        ServerBootstrap serverBoostrap = new ServerBootstrap();


        NioEventLoopGroup boss = new NioEventLoopGroup();  //boos对应，IOServer.java中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup worker = new NioEventLoopGroup(); //worker对应 IOClient.java中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理


        final AttributeKey childAttr = AttributeKey.newInstance("childAttr");
        serverBoostrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("attr"),"attr")
                .childAttr(childAttr,"childAttr")
                .childHandler(new ChannelInitializer() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new Spliter());
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginRequestHandler());
                        channel.pipeline().addLast(new AuthHandler());
                        channel.pipeline().addLast(new MessageRequestHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                        channel.pipeline().addLast(new CreateGroupRequestHandler());
                        channel.pipeline().addLast(new LogoutRequestHandler());



                    }
                }).childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_BACKLOG,1024);
                bind(serverBoostrap,8000);

    }

    //自动绑定递增端口
    public static void bind(final ServerBootstrap serverBoostrap , final int port){
        serverBoostrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("绑定端口成功,端口号:" + port);
                }
                else{
                    System.out.println("绑定端口:" + port + "失败");
                    bind(serverBoostrap,port+1);
                }
            }
        });
    }

}
