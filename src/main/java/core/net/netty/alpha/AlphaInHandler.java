package core.net.netty.alpha;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import core.net.netty.BaseInHandler;
import core.net.netty.NettyAlphaServer;
import dto.*;
import dto.json.AlphaJsonConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/21
 * (可定制化)
 */
@ChannelHandler.Sharable
public class AlphaInHandler extends SimpleChannelInboundHandler<String> {

    private AlphaJsonConverter alphaJsonConverter=null;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    public AlphaInHandler(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        try {
            System.out.println("服务端收到："+msg);
            Alpha alpha = this.alphaJsonConverter.fromJson(msg);
                //传递给核心得业务处理器
            ctx.fireChannelRead(alpha);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
