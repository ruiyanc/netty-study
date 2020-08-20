package com.yanrui.demo.hello;

import com.sun.xml.internal.bind.marshaller.NioEscapeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author 许睿
 * @version 1.0
 * @description 实现客户端发送一个请求，服务器会返回hello netty
 * @date 2020/8/19 22:51
 */
public class HelloServer {

    public static void main(String[] args) {

        //定义一对线程组
        //主线程组，用于接受客户端的连接，但是不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组，主线程组会把任务丢给他，让线程组做任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            //netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置主从线程组
            serverBootstrap.group(bossGroup, workerGroup)
    //                设置nio的双向通道
                    .channel(NioServerSocketChannel.class)
    //                子处理器，用于处理workerGroup
                    .childHandler(new HelloServerInitializer());

//        启动server，并且设置为8088为启动端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

//            监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
