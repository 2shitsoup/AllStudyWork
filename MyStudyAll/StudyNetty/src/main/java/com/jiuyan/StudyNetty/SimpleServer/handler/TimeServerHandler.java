package com.jiuyan.StudyNetty.SimpleServer.handler;

import com.jiuyan.StudyNetty.SimpleServer.po.UnixTime;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Classname: TimerServerHandler
 * @Description TODO
 * @Date: 2019-09-18 11:55
 * @Created by JiuyanLAY
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        final ChannelFuture channelFuture = ctx.writeAndFlush(time);
        channelFuture.addListener((ChannelFutureListener) f -> {
            assert channelFuture == f;
            ctx.close();
        });*/
        System.out.println("服务端Server---channelActive：发送一条消息。");
        //使用POJO简化
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        //f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
