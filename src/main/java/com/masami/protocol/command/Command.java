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

    Byte JOIN_GROUP_REQUEST = 9;


    Byte JOIN_GROUP_RESPONSE = 10;


    byte LIST_GROUP_MEMBER_REQUEST = 11;

    byte LIST_GROUP_MEMBER_RESPONSE = 12;

    byte QUIT_GROUP_REQUEST = 13;

    byte QUIT_GROUP_RESPONSE =14;


    byte GROUP_MESSAGE_REQUEST = 15;

    byte GROUP_MESSAGE_RESPONSE = 16;



}
