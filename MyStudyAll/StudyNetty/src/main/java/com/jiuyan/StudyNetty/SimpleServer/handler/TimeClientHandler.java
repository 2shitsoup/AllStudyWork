package com.jiuyan.StudyNetty.SimpleServer.handler;

import com.jiuyan.StudyNetty.SimpleServer.po.UnixTime;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Classname: TimeClientHandler
 * @Description TODO
 * @Date: 2019-09-18 15:09
 * @Created by JiuyanLAY
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端Client---收到一条消息。");
        /*ByteBuf m = (ByteBuf) msg; // (1)
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }*/
        //使用POJO简化
        UnixTime time = (UnixTime) msg;
        System.out.println(time);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
