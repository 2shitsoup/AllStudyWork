package com.jiuyan.StudyNetty.WebSocketChat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Classname: WebsocketChatServer
 * @Description TODO
 * @Date: 2019-09-19 13:38
 * @Created by JiuyanLAY
 */
public class WebsocketChatServer {
    private int port;

    public WebsocketChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    //***关键：在这里我们使用一个特殊的类，ChannelInitializer 。
                    // 当一个新的连接被接受，一个新的子 Channel 将被创建， ChannelInitializer 会添加我们XXXXServerHandler 的实例到 Channel 的 ChannelPipeline。
                    // 正如我们如前所述，如果有入站信息，这个处理器将被通知。
                    .childHandler(new WebsocketChatServerInitializer())  //(4)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            System.out.println("WebsocketChatServer 启动了");

            // 绑定端口，开始接收进来的连接
            // （调用 sync() 的原因是使当前线程阻塞）
            ChannelFuture f = b.bind(port).sync(); // (7)

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            System.out.println("WebsocketChatServer 关闭了");
        }
    }

    public static void main(String[] args) throws Exception {
        int port=8088;
        new WebsocketChatServer(port).run();
    }
}
