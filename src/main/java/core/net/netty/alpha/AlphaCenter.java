package core.net.netty.alpha;

import core.net.netty.BaseInHandler;
import dto.Alpha;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/23
 */
@ChannelHandler.Sharable
public class AlphaCenter extends BaseInHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //处理核心
        if (msg instanceof Alpha) {
            //暂时这样测试
            System.out.println(this.getClass() + "  channelRead0   " + msg);
            ctx.channel().write(msg);
        }
    }
}
