package client;

import dto.json.AlphaJsonConverter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 杨能
 * @create 2020/10/22
 */
public class AlphaInHandler extends SimpleChannelInboundHandler<String> {
    AlphaJsonConverter alphaJsonConverter=null;
    public AlphaInHandler(AlphaJsonConverter alphaJsonConverter){
        this.alphaJsonConverter=alphaJsonConverter;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端收到："+msg);
        ctx.writeAndFlush(alphaJsonConverter.fromJson(msg));
    }
}
