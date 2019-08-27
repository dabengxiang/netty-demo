package com.masami.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import sun.misc.Version;

/**
 * Date:2019/8/27
 * Author:gyc
 * Desc:
 */
@Data
public abstract class Packet {

    @JSONField(deserialize = false,serialize = false)
    private byte version = 1;

    public  abstract  byte getCommand();

}
