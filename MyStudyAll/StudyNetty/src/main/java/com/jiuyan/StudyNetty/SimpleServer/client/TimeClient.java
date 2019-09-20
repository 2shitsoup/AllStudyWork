package com.jiuyan.StudyNetty.SimpleServer.client;

import com.jiuyan.StudyNetty.SimpleServer.handler.TimeClientHandler;
import com.jiuyan.StudyNetty.SimpleServer.handler.TimeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Classname: TimeClient
 * @Description TODO
 * @Date: 2019-09-18 14:47
 * @Created by JiuyanLAY
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8088;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                        }
                    });
            // 启动客户端
            ChannelFuture f = boot.connect(host, port).sync();

            // 客户端——等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
