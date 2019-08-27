package com.masami.nettyDemo.serialize;

/**
 * @Author: gyc
 * @Date: 2019/8/27 18:10
 */
public interface Serializer {

    public byte getSerializerAlogrithm();

    byte[] serialize(Object object);

    <T> T deSerialize(Class<T> tClass,byte[] bytes);

}
