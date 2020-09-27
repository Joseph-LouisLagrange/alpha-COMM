package core.net.netty.alpha;

import com.google.gson.Gson;
import core.net.netty.BaseOutHandler;
import dto.Alpha;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author 杨能
 * @create 2020/9/21
 */
public class AlphaOutHandler extends BaseOutHandler {

    private Gson gson;

    public AlphaOutHandler(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Alpha) {
            //JSON
            String jsonString = gson.toJson(msg);
            ctx.writeAndFlush(jsonString);
            return;
        }
        ctx.writeAndFlush(msg);
    }
}
