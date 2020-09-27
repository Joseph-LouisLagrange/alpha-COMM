package core.net.netty.http;

import dto.Alpha;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author 杨能
 * @create 2020/9/23
 */
public class SimpleHttpInHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
            if (request.method().equals(HttpMethod.POST) && !decoder.isMultipart()) {
                //拆包
                String alphaJson = request.content().toString(CharsetUtil.UTF_8);
                //传递
                ctx.fireChannelRead(alphaJson);
                return;
            }
        }
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);
    }
}
