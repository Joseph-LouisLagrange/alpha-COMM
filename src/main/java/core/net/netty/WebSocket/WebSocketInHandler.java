package core.net.netty.WebSocket;

import config.ServerProperties;
import core.net.netty.BaseInHandler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/19
 * Http握手就会升级为WebSocket协议
 */
public abstract class WebSocketInHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof WebSocketFrame) {
            //开始websocket通讯
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
            return;
        }
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);
    }

    protected abstract void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame);
}
