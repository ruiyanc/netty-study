//package com.yanrui.demo.websocket;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import org.springframework.stereotype.Component;
//
///**
// * @author 许睿
// * @version 1.0
// * @description
// * @date 2020/8/20 20:10
// */
//@Component
//public class WSServer {
//
//    //单例启动
//    private static class SingletionWSServer{
//        static final WSServer instance = new WSServer();
//    }
//
//    public static WSServer getInstance() {
//        return SingletionWSServer.instance;
//    }
//
//    private EventLoopGroup mainGroup;
//    private EventLoopGroup subGroup;
//    private ServerBootstrap server;
//    private ChannelFuture future;
//
//    public WSServer() {
//        mainGroup = new NioEventLoopGroup();
//        subGroup = new NioEventLoopGroup();
//        server = new ServerBootstrap();
//        server.group(mainGroup, subGroup)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(null);
//    }
//
//    public void start() {
//        this.future = server.bind(8088);
//        System.err.println("netty 启动完毕!");
//    }
//
//    public static void main(String[] args) {
//        EventLoopGroup mainGroup = new NioEventLoopGroup();
//        EventLoopGroup subGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap server = new ServerBootstrap();
//            server.group(mainGroup, subGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(null);
//
//            ChannelFuture future = server.bind(8088).sync();
//            future.channel().closeFuture().sync();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            mainGroup.shutdownGracefully();
//            subGroup.shutdownGracefully();
//        }
//    }
//}
