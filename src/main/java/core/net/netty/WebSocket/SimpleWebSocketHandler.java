package core.net.netty.WebSocket;

import config.ServerProperties;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

/**
 * @author 杨能
 * @create 2020/9/19
 * 这个类很关键几乎是WebSocket的核心
 */
@ChannelHandler.Sharable
public class SimpleWebSocketHandler extends WebSocketHandler {
    //HTTP握手
    private WebSocketServerHandshaker handshaker;

    public SimpleWebSocketHandler(int port, String path, String ip) {
        super(port, path, ip);
    }

    public SimpleWebSocketHandler(int port, String path) {
        super(port, path);
    }

    public SimpleWebSocketHandler(ServerProperties serverProperties) {
        super(serverProperties);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            //进入Http握手阶段
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }else if(msg instanceof WebSocketFrame){
            //开始websocket通讯
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);

//            TextWebSocketFrame textWebSocketFrame=new TextWebSocketFrame("Hello world");
//            ctx.writeAndFlush(textWebSocketFrame);
            BinaryWebSocketFrame binaryWebSocketFrame=new BinaryWebSocketFrame(Unpooled.copiedBuffer("Hello world",CharsetUtil.UTF_8));
            ctx.writeAndFlush(binaryWebSocketFrame);
        }
    }

    @Override
    protected void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        //要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://"+getIp()+":"+getPort()+getPath(), null, false,65536);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    @Override
    protected void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //  这是数据包分片
        if(frame instanceof  ContinuationWebSocketFrame){
            System.out.println("ContinuationWebSocketFrame :"+frame.toString());
            ContinuationWebSocketFrame continuationWebSocketFrame= (ContinuationWebSocketFrame) frame;
            continuationWebSocketFrame.content().clear();
        }
        //二进制数据包
        if(frame instanceof BinaryWebSocketFrame){
            BinaryWebSocketFrame webSocketFrame= (BinaryWebSocketFrame) frame;
            System.out.println("BinaryWebSocketFrame:"+frame.toString());
           // System.out.println(webSocketFrame.content().toString(CharsetUtil.UTF_8).length());
        }
        //数据包的结束位出现
        if(frame.isFinalFragment()){
            System.out.println("最后一个桢到了:"+frame);
        }
        //文本类数据包
        if (frame instanceof TextWebSocketFrame) {
            System.out.println("收到："+((TextWebSocketFrame) frame).text());
        }
        // 返回【谁发的发给谁】
        // ctx.channel().writeAndFlush(tws);
    }

    /**
     * 拒绝不合法的请求，并返回错误信息
     * */
    @Override
    protected void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
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

}
