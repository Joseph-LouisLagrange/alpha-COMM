package core.net.netty.WebSocket;

import com.google.gson.Gson;
import dto.Alpha;
import dto.BaseProtocol;
import dto.json.AlphaJsonConverter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author 杨能
 * @create 2020/9/23
 */
public class SimpleWebSocketOutHandler extends ChannelOutboundHandlerAdapter {

    private AlphaJsonConverter alphaJsonConverter;

    public SimpleWebSocketOutHandler(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Alpha) {
            Alpha alpha = (Alpha) msg;
            if (alpha.getBaseProtocol() == BaseProtocol.WEBSOCKET) {
                ctx.writeAndFlush(new TextWebSocketFrame(this.alphaJsonConverter.toJson(alpha)));
                return;
            }
        }
        super.write(ctx, msg, promise);
    }
}
