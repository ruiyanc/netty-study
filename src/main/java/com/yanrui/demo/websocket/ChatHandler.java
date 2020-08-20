package com.yanrui.demo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @author 许睿
 * @version 1.0
 * @description 处理消息的handler
 * TextWebSocketFrame：用于处理websocket专门处理文本的对象，frame是消息的载体
 * @date 2020/8/20 20:25
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的channel
     */
    private static final ChannelGroup CLIENTS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
//        获取客户端传输过来的消息
        String content = textWebSocketFrame.text();
        System.out.println("接受到的数据：" + content);
//        for (Channel channel : clients) {
//            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]"
//                    + LocalDateTime.now() + "接受消息，消息为：" + content));
//        }
        //ChannelGroup的这方法和上面的for循环一致
        CLIENTS.writeAndFlush(new TextWebSocketFrame("[服务器在]"
                + LocalDateTime.now() + "接受消息，消息为：" + content));
    }

    /**
     * 当客户端连接服务器之后，获取客户端的channel，并且放到ChannelGroup中进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.channel();
        CLIENTS.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        当触发handlerRemoved时ChannelGroup会自动移除对应客户端的channel
        System.out.println("客户端断开channel对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开channel对应的短id为：" + ctx.channel().id().asShortText());
    }
}
