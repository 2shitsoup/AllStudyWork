package com.jiuyan.StudyNetty.SimpleServer.po;

import java.util.Date;

/**
 * @Classname: UnixTime
 * @Description TODO
 * @Date: 2019-09-18 16:30
 * @Created by JiuyanLAY
 */
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
