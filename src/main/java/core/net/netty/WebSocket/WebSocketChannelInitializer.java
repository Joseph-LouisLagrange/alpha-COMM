package core.net.netty.WebSocket;

import core.net.netty.NettyAlphaServer;
import core.net.netty.NettyChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 杨能
 * @create 2020/9/19
 * WebSocket特定的ChannelInitializer
 */
public class WebSocketChannelInitializer extends NettyChannelInitializer {

    private WebSocketHandler webSocketHandler;

    public WebSocketChannelInitializer(WebSocketHandler webSocketHandler) {
        this.setWebSocketHandler(webSocketHandler);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("http-codec",new HttpServerCodec());//设置解码器
        ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
        ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());//用于大数据的分区传输
        //设置自己的handler
        ch.pipeline().addLast(getWebSocketHandler());
    }

    public WebSocketHandler getWebSocketHandler() {
        return webSocketHandler;
    }

    public void setWebSocketHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }
}
