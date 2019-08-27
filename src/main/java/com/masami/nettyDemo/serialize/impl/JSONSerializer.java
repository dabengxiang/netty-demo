package com.masami.nettyDemo.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.masami.nettyDemo.serialize.Serializer;
import com.masami.nettyDemo.serialize.SerializerAlogrithm;

/**
 * @Author: gyc
 * @Date: 2019/8/27 18:15
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deSerialize(Class<T> tClass, byte[] bytes) {
        return JSON.parseObject(bytes,tClass);
    }
}
