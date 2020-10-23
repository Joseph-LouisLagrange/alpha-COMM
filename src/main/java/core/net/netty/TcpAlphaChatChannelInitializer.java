package core.net.netty;

import core.net.netty.alpha.AlphaCenter;
import core.net.netty.alpha.AlphaInHandler;
import core.net.netty.alpha.AlphaOutHandler;
import core.net.netty.http.SimpleHttpOutHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author 杨能
 * @create 2020/10/8
 */
@ChannelHandler.Sharable
public class TcpAlphaChatChannelInitializer extends AlphaChatChannelInitializer {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new AlphaInHandler(nettyAlphaServer.getAlphaJsonConverter()));
        ch.pipeline().addLast(new AlphaCenter(nettyAlphaServer));
        ch.pipeline().addLast(new AlphaOutHandler(nettyAlphaServer.getAlphaJsonConverter()));
        //显式出站
        //ch.pipeline().addLast(new SimpleHttpOutHandler(nettyAlphaServer.getAlphaJsonConverter()));
    }
}
