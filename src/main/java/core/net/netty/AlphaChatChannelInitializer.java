package core.net.netty;


import core.net.netty.WebSocket.WebSocketHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 * WebSocket特定的ChannelInitializer
 */
public class AlphaChatChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected NettyAlphaServer nettyAlphaServer;

    List<ChannelHandler> channelHandlerList;

    public AlphaChatChannelInitializer(List<ChannelHandler> channelHandlerList, NettyAlphaServer nettyAlphaServer) {
        this.nettyAlphaServer = nettyAlphaServer;
        this.channelHandlerList = channelHandlerList;
    }

    public AlphaChatChannelInitializer(List<ChannelHandler> channelHandlerList) {
        this.channelHandlerList = channelHandlerList;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        channelHandlerList.forEach(ch.pipeline()::addLast);

        //设置自己的handler
    }

    public NettyAlphaServer getNettyAlphaServer() {
        return nettyAlphaServer;
    }

    public void setNettyAlphaServer(NettyAlphaServer nettyAlphaServer) {
        this.nettyAlphaServer = nettyAlphaServer;
    }
}
