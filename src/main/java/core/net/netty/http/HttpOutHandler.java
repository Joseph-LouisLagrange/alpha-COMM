package core.net.netty.http;

import core.net.netty.BaseInHandler;
import core.net.netty.BaseOutHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;

/**
 * @author 杨能
 * @create 2020/9/21
 */
public abstract class HttpOutHandler extends BaseOutHandler {

    protected void setCORS(FullHttpResponse fullHttpResponse) {
        fullHttpResponse.headers().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        fullHttpResponse.headers().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "*");
    }

    protected abstract void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req);


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
    }
}
