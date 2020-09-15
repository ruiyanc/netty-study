package com.yanrui.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 许睿
 * @version 1.0
 * @description
 * @date 2020/8/20 20:10
 */
@Component
public class WsServer {

    //单例启动
    private static class SingletionWSServer{
        static final WsServer instance = new WsServer();
    }

    public static WsServer getInstance() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WsServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitializer());
    }

    @PostConstruct
    public void start() {
        this.future = server.bind(8088);
        System.err.println("netty 启动完毕!");
    }

}
