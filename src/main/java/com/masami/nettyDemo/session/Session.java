package com.masami.nettyDemo.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: gyc
 * @Date: 2019/8/30 14:21
 */
@Data
@ToString
@NoArgsConstructor
public class Session {

    private String userId;

    private String userName;


    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
