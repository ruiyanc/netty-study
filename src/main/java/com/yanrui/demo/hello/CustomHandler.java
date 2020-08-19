package com.yanrui.demo.hello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author 许睿
 * @version 1.0
 * @description 创建自定义的助手类
 * SimpleChannelInboundHandler：对于请求而言相当于入站
 * @date 2020/8/20 1:05
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
//        获取channel
        Channel channel = channelHandlerContext.channel();

        if (httpObject instanceof HttpRequest) {
//          显示客户端的远程地址
            System.out.println(channel.remoteAddress());

//          定义发送的数据消息
            ByteBuf content = Unpooled.copiedBuffer("Hello netty!!!", CharsetUtil.UTF_8);

//          构建一个http response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);

//          为响应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

//           把响应刷到客户端
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
