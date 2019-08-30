package com.masami.nettyDemo.utils;

import com.masami.nettyDemo.attributes.Attributes;
import com.masami.nettyDemo.session.Session;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: gyc
 * @Date: 2019/8/30 14:13
 */
public class SessionUtil  {


    private static Map<String , Channel> userIdChannelMap = new ConcurrentHashMap();


    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);

    }


    public  void unBindSession(Channel channel){

    }


    public static  boolean isLogin(Channel channel){
        Attribute<Session> attr = channel.attr(Attributes.SESSION);
        return attr.get()!=null;

    }
}
