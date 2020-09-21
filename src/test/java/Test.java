import config.ServerProperties;
import core.net.netty.FirstInHandler;
import core.net.netty.NettyAlphaServer;

import core.net.netty.WebSocket.SimpleWebSocketHandler;
import core.net.netty.AlphaChatChannelInitializer;
import core.net.netty.alpha.AlphaInHandler;
import core.net.netty.alpha.AlphaOutHandler;
import core.net.netty.http.WebSocketHttpAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class Test {
    public static void main(String[] args) {
//        FastAlphaRepositoryFactory fastAlphaRepositoryFactory=FastAlphaRepositoryFactory.getInstance();
//        AlphaRepository alphaRepository=fastAlphaRepositoryFactory.getEmptyAlphaRepository();
//        alphaRepository.setPrivate(null);
//        SimpleCenterRepository centerRepository=SimpleCenterRepository.getInstance();
//        centerRepository.addAlphaRepository(alphaRepository);
//        System.out.println(centerRepository.getAllAlphaRepository().get(0).toString());
        ServerProperties serverProperties = new ServerProperties();//配置
        CorsConfigBuilder corsConfigBuilder = CorsConfigBuilder.forAnyOrigin();
        corsConfigBuilder.allowedRequestMethods(HttpMethod.GET, HttpMethod.CONNECT, HttpMethod.DELETE,
                HttpMethod.OPTIONS, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.TRACE)
                .allowCredentials()
                .maxAge(-1);
        List<ChannelHandler> channelHandlerList = new ArrayList<>();

        channelHandlerList.add(new FirstInHandler());
        channelHandlerList.add(new HttpServerCodec());
        channelHandlerList.add(new HttpObjectAggregator(65536));
        channelHandlerList.add(new CorsHandler(corsConfigBuilder.build()));
        channelHandlerList.add(new ChunkedWriteHandler());
        channelHandlerList.add(new WebSocketHttpAdapter(serverProperties));
        channelHandlerList.add(new SimpleWebSocketHandler());
        channelHandlerList.add(new AlphaInHandler());
        channelHandlerList.add(new AlphaOutHandler());
        AlphaChatChannelInitializer nettyChannelInitializer = new AlphaChatChannelInitializer(channelHandlerList);//中间件
        NettyAlphaServer nettyAlphaServer = new NettyAlphaServer(serverProperties, nettyChannelInitializer);//创建服务器
        nettyAlphaServer.start();//开启服务器
    }
}
