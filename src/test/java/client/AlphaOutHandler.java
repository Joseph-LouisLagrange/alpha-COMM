package client;

import dto.Alpha;
import dto.json.AlphaJsonConverter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author 杨能
 * @create 2020/10/22
 */
public class AlphaOutHandler extends ChannelOutboundHandlerAdapter {
    AlphaJsonConverter alphaJsonConverter=null;
    public AlphaOutHandler(AlphaJsonConverter alphaJsonConverter){
        this.alphaJsonConverter=alphaJsonConverter;
    }
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //super.write(ctx, msg, promise);
        if(msg instanceof Alpha){
            msg=alphaJsonConverter.toJson((Alpha) msg);
            System.out.println("客户端发出："+msg);
        }
        ctx.writeAndFlush(msg, promise);
    }
}
