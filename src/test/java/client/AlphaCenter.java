package client;

import dto.Alpha;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 杨能
 * @create 2020/10/22
 */
public class AlphaCenter extends SimpleChannelInboundHandler<Alpha> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Alpha msg) throws Exception {

    }
}
