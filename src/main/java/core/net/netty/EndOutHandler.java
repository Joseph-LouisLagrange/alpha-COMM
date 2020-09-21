package core.net.netty;

import core.net.netty.BaseOutHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @author 杨能
 * @create 2020/9/21
 */
public class EndOutHandler extends BaseOutHandler {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //super.write(ctx, msg, promise);
        ctx.writeAndFlush(msg);
    }
}
