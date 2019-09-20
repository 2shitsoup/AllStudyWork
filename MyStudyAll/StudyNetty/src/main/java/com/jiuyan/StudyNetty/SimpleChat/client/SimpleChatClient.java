package com.jiuyan.StudyNetty.SimpleChat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Classname: SimpleChatClient
 * @Description TODO
 * @Date: 2019-09-18 17:59
 * @Created by JiuyanLAY
 */
public class SimpleChatClient {
    public void run() {
        String host = "localhost";
        int port = 8088;

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer());
            Channel channel = boot.connect(host, port).sync().channel();
            System.out.println("你已连接success!  可以聊天了。");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new SimpleChatClient().run();
    }
}
