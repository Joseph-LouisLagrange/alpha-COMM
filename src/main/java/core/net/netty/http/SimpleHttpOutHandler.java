package core.net.netty.http;

import com.google.gson.Gson;
import dto.Alpha;
import dto.BaseProtocol;
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

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Alpha) {
            Alpha alpha = (Alpha) msg;
            if (alpha.getBaseProtocol() == BaseProtocol.HTTP) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(alpha);
                ByteBuf buf = Unpooled.copiedBuffer(jsonString, CharsetUtil.UTF_8);
                FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + ";charset=utf-8");
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
                System.out.println(this.getClass() + " fullHttpResponse: " + fullHttpResponse);
                //super.write(ctx, fullHttpResponse, promise);
                ctx.writeAndFlush(fullHttpResponse);
                return;
            }
            //ReferenceCountUtil.release(msg);
        }
        super.write(ctx, msg, promise);
    }
}
