package core.net.netty.alpha;

import core.net.netty.BaseInHandler;
import core.net.netty.NettyAlphaServer;
import dto.Alpha;
import dto.BaseProtocol;
import dto.alphaUtil.GenericAlpha;
import dto.endpoint.AnonymousUserEndpoint;
import dto.endpoint.Endpoint;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;

/**
 * @author 杨能
 * @create 2020/9/23
 * 核心业务层(可定制化)
 */
@ChannelHandler.Sharable
public class AlphaCenter extends SimpleChannelInboundHandler<Alpha> {
    protected NettyAlphaServer alphaServer;

    public AlphaCenter(NettyAlphaServer alphaServer) {
        this.alphaServer = alphaServer;
    }

    //上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"进入系统,但是未登录");
        alphaServer.active(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Alpha msg) throws Exception {
            //处理核心
            alphaServer.callService(msg,ctx.channel().remoteAddress());
        }

    /**
     * 下线这里指的是被动下线 (物理层面)
     * 如：直接瞬间杀死应用程序 ，或者系统死亡
     * @param ctx Channel的上下文
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress=ctx.channel().remoteAddress();
        boolean isAccess=alphaServer.isAccess(socketAddress);
        if(isAccess){
            //表示处于登陆状态有广播的权利
            Endpoint endpoint=alphaServer.getEndpoint(ctx.channel());
            System.out.println(endpoint+"下线...");
            alphaServer.exit(endpoint);//通行层面先退出
            //模拟一个 像由客户端主动发出的离线广播包
            this.channelRead0(ctx, GenericAlpha.offlineAdviceAlpha(BaseProtocol.TCP, endpoint, new AnonymousUserEndpoint()));
        }
        alphaServer.exit(socketAddress);//物理层面退出
    }
}
