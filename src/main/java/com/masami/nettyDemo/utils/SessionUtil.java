package com.masami.nettyDemo.utils;

import com.masami.nettyDemo.attributes.Attributes;
import com.masami.nettyDemo.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: gyc
 * @Date: 2019/8/30 14:13
 */
public class SessionUtil  {


    private static Map<String , Channel> userIdChannelMap = new ConcurrentHashMap();

    private static Map<String ,ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();


    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);

    }

    public static void unbindSession(Channel channel){

        if(isLogin(channel)){
            Session session = channel.attr(Attributes.SESSION).get();
            if(session!=null){
                userIdChannelMap.remove(session.getUserId());
            }
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(new Date() +session.getUserId() + " : "+session.getUserName() + "：退出登录成功");
        }

    }


    public static  boolean isLogin(Channel channel){
        Attribute<Session> attr = channel.attr(Attributes.SESSION);
        return attr.get()!=null;

    }


    public static  Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();

    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup){
        channelGroupMap.put(groupId,channelGroup);
    }


    public static ChannelGroup getChannelGroup(String groupId){
        return channelGroupMap.get(groupId);
    }

}
