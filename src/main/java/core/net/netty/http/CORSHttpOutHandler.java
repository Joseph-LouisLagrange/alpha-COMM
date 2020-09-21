package core.net.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * @author 杨能
 * @create 2020/9/21
 */
public class CORSHttpOutHandler extends HttpOutHandler {

    @Override
    protected void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.ACCEPTED);
        setCORS(fullHttpResponse);

        ctx.writeAndFlush(fullHttpResponse);
    }
}
