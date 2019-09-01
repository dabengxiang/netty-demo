package com.masami.protocol.command;

/**
 * Date:2019/8/27
 * Author:gyc
 * Desc:
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST= 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte LOG_OUT_REQUEST= 5;

    Byte LOG_OUT_RESPONSE= 6;

    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;



}
