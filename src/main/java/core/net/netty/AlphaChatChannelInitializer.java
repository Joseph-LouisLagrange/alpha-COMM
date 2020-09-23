package core.net.netty;


import core.net.netty.WebSocket.SimpleWebSocketInHandler;
import core.net.netty.WebSocket.SimpleWebSocketOutHandler;
import core.net.netty.alpha.AlphaCenter;
import core.net.netty.alpha.AlphaInHandler;
import core.net.netty.alpha.AlphaOutHandler;
import core.net.netty.http.HttpWebSocketAdapter;
import core.net.netty.http.SimpleHttpInHandler;
import core.net.netty.http.SimpleHttpOutHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 * WebSocket特定的ChannelInitializer
 */
@ChannelHandler.Sharable
public class AlphaChatChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected NettyAlphaServer nettyAlphaServer;


    public AlphaChatChannelInitializer(NettyAlphaServer nettyAlphaServer) {
        this.nettyAlphaServer = nettyAlphaServer;
    }

    public AlphaChatChannelInitializer() {

    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        CorsConfigBuilder corsConfigBuilder = CorsConfigBuilder.forAnyOrigin();
        corsConfigBuilder.allowedRequestMethods(HttpMethod.GET, HttpMethod.CONNECT, HttpMethod.DELETE,
                HttpMethod.OPTIONS, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.TRACE)
                .allowCredentials()
                .maxAge(-1);
        ch.pipeline().addLast(new EndOutHandler());
        ch.pipeline().addLast(new HttpServerCodec());
        ch.pipeline().addLast(new HttpObjectAggregator(65536));
        ch.pipeline().addLast(new ChunkedWriteHandler());
        // 用于下载文件
        //ch.pipeline().addLast(new HttpDownloadHandler());
        ch.pipeline().addLast(new CorsHandler(corsConfigBuilder.build()));
        ch.pipeline().addLast(new HttpWebSocketAdapter("localhost", 8081, "/alpha"));
        ch.pipeline().addLast(new SimpleWebSocketInHandler());
        ch.pipeline().addLast(new SimpleHttpInHandler());
        //设置自己的handler
        ch.pipeline().addLast(new AlphaInHandler());
        ch.pipeline().addLast(new AlphaCenter());
        //显式出站
        ch.pipeline().addLast(new SimpleHttpOutHandler());
        ch.pipeline().addLast(new SimpleWebSocketOutHandler());
    }

    public NettyAlphaServer getNettyAlphaServer() {
        return nettyAlphaServer;
    }

    public void setNettyAlphaServer(NettyAlphaServer nettyAlphaServer) {
        this.nettyAlphaServer = nettyAlphaServer;
    }
}
