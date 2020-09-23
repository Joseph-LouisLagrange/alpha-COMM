package core.net.netty.http;

import config.ServerProperties;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

/**
 * @author 杨能
 * @create 2020/9/21
 * 把HTTP转向WebSocket的类，把Http适配为WebSocket协议
 */
public class HttpWebSocketAdapter extends SimpleChannelInboundHandler<Object> {

    private final String ip;
    private final int port;
    private final String path;

    public HttpWebSocketAdapter(String ip, int port, String path) {
        this.ip = ip;
        this.port = port;
        this.path = path;
    }

    public HttpWebSocketAdapter(ServerProperties serverProperties) {
        this(serverProperties.getIp(), serverProperties.getPort(), serverProperties.getWebSocketPath());
    }

    //HTTP握手，这是WebSocket开始的预兆
    private WebSocketServerHandshaker handshaker;

    /**
     * 把http协议适配为Websocket协议
     *
     * @param ctx ChannelHandlerContext
     * @param req FullHttpRequest
     */
    protected void httpToWebSocket(ChannelHandlerContext ctx, FullHttpRequest req) {
        String webSocketURL = "ws://" + ip + ":" + port + path;
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                webSocketURL, null, false, 65536);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 拒绝不合法的请求，并返回错误信息
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        // 如果是非Keep-Alive，关闭连接
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest
                && ((FullHttpRequest) msg).decoderResult().isSuccess()
                && "websocket".equals(((FullHttpRequest) msg).headers().get("Upgrade"))) {
            httpToWebSocket(ctx, (FullHttpRequest) msg);//开始协议适配
            return;
        }
        //直接传递到下级
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);//传递下级
    }
}
