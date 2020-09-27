package core.net.netty.alpha;

import core.net.AlphaServer;
import core.net.netty.BaseInHandler;
import core.net.netty.NettyAlphaServer;
import dto.Alpha;
import dto.BaseProtocol;
import dto.alphas.GenericAlpha;
import dto.endpoint.Endpoint;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/23
 * 核心业务层(可定制化)
 */
@ChannelHandler.Sharable
public class AlphaCenter extends BaseInHandler {
    protected NettyAlphaServer alphaServer;

    public AlphaCenter(NettyAlphaServer alphaServer) {
        this.alphaServer = alphaServer;
    }

    //上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //处理核心
        if (msg instanceof Alpha) {
            Alpha alpha = (Alpha) msg;
            Endpoint from = alpha.getFrom();
            if (!alphaServer.isActive(from)) {
                //激活这个通道，但是没有登陆
                alphaServer.active(alpha.getFrom(), ctx.channel());
            }
            alphaServer.callService(alpha);
            ctx.channel().write(msg);
        }
    }

    //下线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Endpoint endpoint = alphaServer.getEndpoint(ctx.channel());//获取离开的人
        alphaServer.exit(endpoint);//优雅退出
        //发出离线广播
        this.channelRead0(ctx, GenericAlpha.offlineAdviceAlpha(BaseProtocol.WEBSOCKET, endpoint, null));
    }
}
