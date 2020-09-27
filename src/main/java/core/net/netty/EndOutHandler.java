package core.net.netty;

import core.net.netty.BaseOutHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

/**
 * @author 杨能
 * @create 2020/9/21
 */
@ChannelHandler.Sharable
public class EndOutHandler extends BaseOutHandler {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //System.out.println(this.getClass() + " write  " + msg);
        ctx.writeAndFlush(msg);
    }
}
