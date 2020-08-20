package com.yanrui.demo.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 许睿
 * @version 1.0
 * @description
 * @date 2020/8/20 20:15
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

//        websocket基于http协议，所有要有http编解码器
        pipeline.addLast(new HttpServerCodec());

//        对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

//        对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpRequest
//        netty编程中都会用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        //====================以上用于支持http协议=================

        //websocket服务器处理的协议，用于指定给客户端连接访问的路由：/ws
        //本handler会处理一些复杂的操作，握手动作：handshaking（close，ping，pong）
        //对于socket来说，都是以frames进行传输的，不同的数据类型对应的也不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义的handler
        pipeline.addLast(new ChatHandler());
    }
}
