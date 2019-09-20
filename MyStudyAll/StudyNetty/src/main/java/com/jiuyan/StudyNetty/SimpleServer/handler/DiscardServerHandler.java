package com.jiuyan.StudyNetty.SimpleServer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Classname: DiscardServerHandler
 * @Description 丢弃服务器
 * @Date: 2019-09-18 10:52
 * @Created by JiuyanLAY
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("DiscardServerHandler---收到一条消息");
        /*// 默默地丢弃收到的数据
        ((ByteBuf) msg).release();*/

        //查看收到的数据
        /*try {
            ByteBuf in = (ByteBuf) msg;
            *//*while (in.isReadable()){
                System.out.println((char) in.readByte());
                System.out.flush();
            }*//*
            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
        } finally {
            ReferenceCountUtil.release(msg);
        }*/

        //写个应答服务器
        //请注意:不同于 DISCARD 的例子我们并没有释放接受到的消息，这是因为当写入的时候 Netty 已经帮我们释放了。
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("DiscardServerHandler---发生异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
