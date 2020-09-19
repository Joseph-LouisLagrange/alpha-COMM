package core.net.netty.WebSocket;

import config.ServerProperties;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public abstract class WebSocketHandler extends SimpleChannelInboundHandler<Object> {
    protected int port;
    protected String path;
    protected String ip;
    public WebSocketHandler(int port , String path , String ip){
        this.setPath(path);
        this.setPort(port);
        this.setIp(ip);
    }

    public WebSocketHandler(ServerProperties serverProperties){
        this(serverProperties.getPort(),serverProperties.getPath(),serverProperties.getIp());
    }

    public WebSocketHandler(int port , String path){
       this(port,path,"127.0.0.1");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    protected abstract void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req);
    protected abstract void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame);
    protected abstract void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res);
}
