package com.yanrui.demo.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author 许睿
 * @version 1.0
 * @description 初始化器，channel注册后会执行里面的相应的初始化方法
 * @date 2020/8/19 23:15
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        通过SocketChannel去获得对应的管道
        ChannelPipeline pipeline = socketChannel.pipeline();

//        通过管道添加handler，HttpServerCodec是netty提供的助手类，等价于拦截器
//        当请求到服务器，需要做解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

//        添加自定义的助手类，返回hello netty
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
