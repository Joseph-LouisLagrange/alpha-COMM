package core.net.netty.http;

import core.net.AlphaServer;
import dto.*;
import dto.dut.*;
import dto.dut.comm.FileAttributes;
import dto.dut.comm.FileDataUnit;
import dto.json.AlphaJsonConverter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.ReferenceCountUtil;

import javax.activation.MimeTypeParseException;
import java.io.IOException;
import java.util.*;

/**
 * @author 杨能
 * @create 2020/9/25
 * 处理MultipartHttp的入站请求
 * 有文件传输的通信
 */
public class MultipartHttpInHandler extends SimpleChannelInboundHandler<Object> {

    private AlphaJsonConverter alphaJsonConverter;

    public MultipartHttpInHandler(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }

    private Alpha handleMultipart(ChannelHandlerContext ctx, HttpPostRequestDecoder decoder) throws IOException, NoSuchFieldException, IllegalAccessException, MimeTypeParseException {
        Alpha alpha = new Alpha();
        List<FileDataUnit> fileDataUnitList = new ArrayList<>();
        while (decoder.hasNext()) {
            InterfaceHttpData httpData = decoder.next();
            if (httpData instanceof Attribute) {
                //属性键对
                Attribute attr = (Attribute) httpData;
                String name = attr.getName();
                if (!name.equalsIgnoreCase("alpha")) {
                    return null;
                }
                String value = attr.getString(AlphaServer.charset);
                alpha = this.alphaJsonConverter.fromJson(value);
            } else if (httpData instanceof FileUpload) {
                //文件传输
                FileUpload fileUpload = (FileUpload) httpData;
                FileDataUnit fileDataUnit = new FileDataUnit(new FileAttributes(fileUpload), fileUpload.get());
                fileDataUnitList.add(fileDataUnit);
            }
        }
        //把零时文件放入alpha包中
        List<DataUnit> dataUnitList = alpha.getBody();
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < dataUnitList.size(); i++) {
            if (dataUnitList.get(i) instanceof FileDataUnit) {
                index.add(i);
            }
        }
        for (int i = 0; i < fileDataUnitList.size(); i++) {
            dataUnitList.set(index.get(i), fileDataUnitList.get(i));
        }
        return alpha;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
            if (decoder.isMultipart()) {
                Alpha alpha = handleMultipart(ctx, decoder);
                ctx.fireChannelRead(alpha);
                return;
            }
        }
        ReferenceCountUtil.retain(msg);
        ctx.fireChannelRead(msg);
    }
}
