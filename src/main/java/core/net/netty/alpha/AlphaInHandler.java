package core.net.netty.alpha;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import core.net.netty.BaseInHandler;
import dto.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/21
 */
@ChannelHandler.Sharable
public class AlphaInHandler extends BaseInHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            try {
                Gson gson = new Gson();
                Alpha alpha = gson.fromJson((String) msg, Alpha.class);
                //Alpha alpha=new Alpha(1,new PersonEndPoint(),new PersonEndPoint(), DataType.REQUEST, Action.ADD_FRIEND,BaseProtocol.HTTP,new Body());
                //传递给核心得业务处理器
                ctx.fireChannelRead(alpha);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                //System.out.println(e);
            }
        }
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);
    }
}
