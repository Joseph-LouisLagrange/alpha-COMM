package core.net.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public abstract class  NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected NettyAlphaServer nettyAlphaServer;

    public NettyAlphaServer getNettyAlphaServer() {
        return nettyAlphaServer;
    }

    public void setNettyAlphaServer(NettyAlphaServer nettyAlphaServer) {
        this.nettyAlphaServer = nettyAlphaServer;
    }
}
