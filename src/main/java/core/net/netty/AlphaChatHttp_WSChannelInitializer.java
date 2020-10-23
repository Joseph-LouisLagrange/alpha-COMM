package core.net.netty;


import core.net.netty.WebSocket.SimpleWebSocketInHandler;
import core.net.netty.WebSocket.SimpleWebSocketOutHandler;
import core.net.netty.alpha.AlphaCenter;
import core.net.netty.alpha.AlphaInHandler;
import core.net.netty.alpha.AlphaOutHandler;
import core.net.netty.http.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
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
public class AlphaChatHttp_WSChannelInitializer extends AlphaChatChannelInitializer {

    protected NettyAlphaServer nettyAlphaServer;

    protected CorsConfigBuilder corsConfigBuilder;

    {
        //跨域支持
        corsConfigBuilder = CorsConfigBuilder.forAnyOrigin();
        corsConfigBuilder.allowedRequestMethods(HttpMethod.GET, HttpMethod.CONNECT, HttpMethod.DELETE,
                HttpMethod.OPTIONS, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.TRACE)
                .allowCredentials()
                .maxAge(-1);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new EndOutHandler());
        ch.pipeline().addLast(new HttpServerCodec());
        ch.pipeline().addLast(new CorsHandler(corsConfigBuilder.build()));
        //聚合器，如何后期老子要开启分块传输就关闭 了
        ch.pipeline().addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        //快写，零拷贝传输
        ch.pipeline().addLast(new ChunkedWriteHandler());
        //数据压缩机
        ch.pipeline().addLast(new HttpContentCompressor());
        ch.pipeline().addLast(new HttpWebSocketAdapter("localhost", 8081, "/alpha"));
        ch.pipeline().addLast(new SimpleWebSocketInHandler());
        ch.pipeline().addLast(new SimpleHttpInHandler());
        ch.pipeline().addLast(new MultipartHttpInHandler(nettyAlphaServer.getAlphaJsonConverter()));
        //设置自己的handler
        ch.pipeline().addLast(new AlphaInHandler(nettyAlphaServer.getAlphaJsonConverter()));
        ch.pipeline().addLast(new AlphaCenter(nettyAlphaServer));
        //显式出站
        ch.pipeline().addLast(new SimpleHttpOutHandler(nettyAlphaServer.getAlphaJsonConverter()));
        ch.pipeline().addLast(new SimpleWebSocketOutHandler(nettyAlphaServer.getAlphaJsonConverter()));
    }
}
