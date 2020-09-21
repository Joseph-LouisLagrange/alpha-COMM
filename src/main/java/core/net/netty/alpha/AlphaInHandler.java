package core.net.netty.alpha;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import core.net.netty.BaseInHandler;
import dto.Alpha;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 杨能
 * @create 2020/9/21
 */
public class AlphaInHandler extends BaseInHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Gson gson = new Gson();
        if (msg instanceof String) {
            Alpha alpha = gson.fromJson((String) msg, Alpha.class);
            //传递给核心得业务处理器
            ctx.fireChannelRead(alpha);
        }
    }
}
