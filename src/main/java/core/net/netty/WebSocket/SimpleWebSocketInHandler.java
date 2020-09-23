package core.net.netty.WebSocket;


import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;


/**
 * @author 杨能
 * @create 2020/9/19
 */
@ChannelHandler.Sharable
public class SimpleWebSocketInHandler extends WebSocketInHandler {

    @Override
    protected void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            //System.out.println("webSocket 关闭");
            ctx.close();//那我也关闭了
            return;
        }
        // 判断是否ping消息(回复心跳)
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //  这是数据包分片
        if(frame instanceof  ContinuationWebSocketFrame){
            ContinuationWebSocketFrame continuationWebSocketFrame= (ContinuationWebSocketFrame) frame;
        }
        //二进制数据包
        if(frame instanceof BinaryWebSocketFrame){
            BinaryWebSocketFrame webSocketFrame = (BinaryWebSocketFrame) frame;
            //System.out.println("BinaryWebSocketFrame:" + frame.content().toString(CharsetUtil.UTF_8));
        }
        //数据包的结束位出现
        if (frame.isFinalFragment()) {
            //System.out.println("最后一个桢到了:" + frame);
        }
        //文本类数据包
        if (frame instanceof TextWebSocketFrame) {
            //提取出内容传递下去(拆包)
            ctx.fireChannelRead(((TextWebSocketFrame) frame).text());
        }
    }
}
