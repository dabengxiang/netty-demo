package com.masami.nettyDemo.utils;


import com.masami.nettyDemo.attributes.Attributes;
import com.masami.nettyDemo.session.Session;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Author: gyc
 * @Date: 2019/8/29 10:55
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static  boolean isLogin(Channel channel){
        Attribute<Session> attr = channel.attr(Attributes.SESSION);
        return attr.get()!=null;

    }

}
