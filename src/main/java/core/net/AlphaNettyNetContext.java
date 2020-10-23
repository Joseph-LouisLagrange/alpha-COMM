package core.net;

import io.netty.channel.Channel;

import java.net.SocketAddress;

/**
 * @author 杨能
 * @create 2020/10/23
 */
public class AlphaNettyNetContext implements AlphaNetContext {
    SocketAddress socketAddress=null;

    public AlphaNettyNetContext(Channel channel){
        socketAddress=channel.remoteAddress();
    }


    @Override
    public SocketAddress getSocketAddress() {
        return this.socketAddress;
    }
}
