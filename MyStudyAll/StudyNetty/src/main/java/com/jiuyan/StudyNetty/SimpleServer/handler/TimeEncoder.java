package com.jiuyan.StudyNetty.SimpleServer.handler;

import com.jiuyan.StudyNetty.SimpleServer.po.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Classname: TimeEncoder
 * @Description TODO
 * @Date: 2019-09-18 16:39
 * @Created by JiuyanLAY
 */
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, UnixTime msg, ByteBuf out) throws Exception {
        System.out.println("TimeEncoder---服务端发送数据编码。");
        out.writeInt((int) msg.value());
    }
}
