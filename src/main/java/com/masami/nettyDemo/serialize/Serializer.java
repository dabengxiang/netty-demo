package com.masami.nettyDemo.serialize;

import com.masami.nettyDemo.serialize.impl.JSONSerializer;

/**
 * @Author: gyc
 * @Date: 2019/8/27 18:10
 */
public interface Serializer {

    public final static Serializer DEFAULT = new JSONSerializer();


    public byte getSerializerAlogrithm();

    byte[] serialize(Object object);

    <T> T deSerialize(Class<T> tClass,byte[] bytes);

}
