package com.masami.nettyDemo.attributes;

import com.masami.nettyDemo.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author: gyc
 * @Date: 2019/8/29 10:57
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
