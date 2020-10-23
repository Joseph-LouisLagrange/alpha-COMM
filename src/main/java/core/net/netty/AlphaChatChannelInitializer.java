package core.net.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author 杨能
 * @create 2020/10/21
 */
public abstract class AlphaChatChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected NettyAlphaServer nettyAlphaServer;
    public AlphaChatChannelInitializer(){

    }
    public NettyAlphaServer getNettyAlphaServer() {
        return nettyAlphaServer;
    }

    public void setNettyAlphaServer(NettyAlphaServer nettyAlphaServer) {
        this.nettyAlphaServer = nettyAlphaServer;
    }
}
