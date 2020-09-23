package core.net.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author 杨能
 * @create 2020/9/23
 */
public class SimpleHttpInHandler extends SimpleChannelInboundHandler<Object> {


    //////////////////////////////////拓展//////////////////
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            if (request.method().equals(HttpMethod.POST)) {
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
                while (decoder.isMultipart() && decoder.hasNext()) {
                    InterfaceHttpData httpData = decoder.next();
                    if (httpData instanceof Attribute) {
                        Attribute attr = (Attribute) httpData;
                        System.out.println("收到mutlipart属性：" + attr);
                    } else if (httpData instanceof FileUpload) {
                        FileUpload fileUpload = (FileUpload) httpData;
                        System.out.println("收到multipart文件：" + fileUpload.getContentType());
                        //可实际保存文件或做其他事情...
                    }
                }
                //拆包
                String alphaJson = request.content().toString(CharsetUtil.UTF_8);
                //传递
                System.out.println(this.getClass() + " alphaJson " + alphaJson);
                ctx.fireChannelRead(alphaJson);
                return;
            }
        }
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);
    }
}