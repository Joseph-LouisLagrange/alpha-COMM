import config.ServerProperties;
import core.net.netty.NettyAlphaServer;
import core.net.netty.NettyChannelInitializer;
import core.net.netty.WebSocket.SimpleWebSocketHandler;
import core.net.netty.WebSocket.WebSocketChannelInitializer;
import core.net.netty.WebSocket.WebSocketHandler;

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
        ServerProperties serverProperties=new ServerProperties();//配置
        WebSocketHandler webSocketHandler=new SimpleWebSocketHandler(serverProperties);//加载Handler
        NettyChannelInitializer nettyChannelInitializer=new WebSocketChannelInitializer(webSocketHandler);//中间件
        NettyAlphaServer nettyAlphaServer=new NettyAlphaServer(serverProperties,nettyChannelInitializer);//创建服务器
        nettyAlphaServer.startNettyServer();//开启服务器
    }
}
