package core.net.netty.alpha;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import core.net.netty.BaseInHandler;
import core.net.netty.NettyAlphaServer;
import dto.*;
import dto.json.AlphaJsonConverter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/21
 * (可定制化)
 */
@ChannelHandler.Sharable
public class AlphaInHandler extends BaseInHandler {

    private AlphaJsonConverter alphaJsonConverter;


    public AlphaInHandler(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            try {
                Alpha alpha = this.alphaJsonConverter.fromJson((String) msg);
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
