package com.jiuyan.StudyNetty.SimpleServer.handler;

import com.jiuyan.StudyNetty.SimpleServer.po.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Classname: TimeDecoder
 * @Description TODO
 * @Date: 2019-09-18 16:16
 * @Created by JiuyanLAY
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        System.out.println("TimeDecoder---缓冲区数据足够解码放入out。");

        //如果够数据字节；就读取到out。
        //out.add(in.readBytes(4));

        //使用POJO
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
