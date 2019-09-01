package com.masami.nettyDemo.utils;

import java.util.UUID;

/**
 * Date:2019/8/31
 * Author:gyc
 * Desc:
 */
public class IDUtil {
    private static int testId = 0;
    public  static  String randomUserId(){
        testId++;
        return testId + "";
//        return UUID.randomUUID().toString().split("-")[0];
    }

}
