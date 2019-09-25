package com.jiuyan.StudyNetty.WebSocketChat.server;

import com.jiuyan.StudyNetty.WebSocketChat.handler.MyHttpRequestHandler;
import com.jiuyan.StudyNetty.WebSocketChat.handler.TextWebSocketFrameHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Classname: WebsocketChatServerInitializer
 * @Description TODO
 * @Date: 2019-09-19 13:36
 * @Created by JiuyanLAY
 */
public class WebsocketChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //HTTP 编码解码器
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(64*1024));
        //这个类提供了支持异步写大数据流不引起高内存消耗。
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new MyHttpRequestHandler("/ws"));
        //WebSocket协议处理器。
        //该类处理协议升级握手以及三个“控制”帧 Close, Ping 和 Pong。
        //Text 和 Binary 数据帧将被传递到下一个处理程序(由你实现)进行处理。
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //WebSocket协议，Text处理器
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
