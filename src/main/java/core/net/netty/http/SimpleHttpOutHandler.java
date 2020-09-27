package core.net.netty.http;

import com.google.gson.Gson;
import core.net.AlphaServer;
import dto.Alpha;
import dto.BaseProtocol;
import dto.json.AlphaJsonConverter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/23
 */
public class SimpleHttpOutHandler extends ChannelOutboundHandlerAdapter {

    private AlphaJsonConverter alphaJsonConverter;

    public SimpleHttpOutHandler(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Alpha) {
            Alpha alpha = (Alpha) msg;
            if (alpha.getBaseProtocol() == BaseProtocol.HTTP) {
                String jsonString = this.alphaJsonConverter.toJson(alpha);
                ByteBuf buf = Unpooled.copiedBuffer(jsonString, AlphaServer.charset);
                FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + ";charset=" + AlphaServer.charset.name());
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
                ctx.writeAndFlush(fullHttpResponse);
                return;
            }
        }
        super.write(ctx, msg, promise);
    }
}
