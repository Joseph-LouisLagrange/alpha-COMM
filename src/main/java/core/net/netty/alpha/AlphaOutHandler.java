package core.net.netty.alpha;

import com.google.gson.Gson;
import core.net.netty.BaseOutHandler;
import dto.Alpha;
import dto.json.AlphaJsonConverter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author 杨能
 * @create 2020/9/21
 */
public class AlphaOutHandler extends ChannelOutboundHandlerAdapter {

    protected AlphaJsonConverter alphaJsonConverter=null;

    public AlphaOutHandler(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter=alphaJsonConverter;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Alpha) {
            //JSON
            msg= alphaJsonConverter.toJson((Alpha) msg);
        }
        System.out.println("服务端发出:" + msg);
        ctx.writeAndFlush(msg, promise);
    }
}
