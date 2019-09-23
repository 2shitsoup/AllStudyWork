package com.jiuyan.StudyNetty.SimpleServer.server;

import com.jiuyan.StudyNetty.SimpleServer.handler.TimeEncoder;
import com.jiuyan.StudyNetty.SimpleServer.handler.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Classname: TimeServer
 * @Description TODO
 * @Date: 2019-09-18 14:47
 * @Created by JiuyanLAY
 */
public class TimeServer {
    private int port;

    public TimeServer(int port){
        this.port=port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap boot =  new ServerBootstrap();
            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //***关键：在这里我们使用一个特殊的类，ChannelInitializer 。
                    // 当一个新的连接被接受，一个新的子 Channel 将被创建， ChannelInitializer 会添加我们XXXXServerHandler 的实例到 Channel 的 ChannelPipeline。
                    // 正如我们如前所述，如果有入站信息，这个处理器将被通知。
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //TimeEncoder,TimeServerHandler。注意顺序。
                            ch.pipeline().addLast(new TimeEncoder(), new TimeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture chf = boot.bind(port).sync();

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            chf.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 8088;
        new TimeServer(port).run();
    }
}
