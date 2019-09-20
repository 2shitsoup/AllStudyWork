package com.jiuyan.StudyNetty.SimpleChat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Classname: SimpleChatClientHandler
 * @Description TODO
 * @Date: 2019-09-18 17:57
 * @Created by JiuyanLAY
 */
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }
}
