import config.ServerProperties;
import core.net.netty.NettyAlphaServer;

import core.net.netty.AlphaChatChannelInitializer;
import service.DayinService;
import dto.dut.DataUnit;
import dto.dut.comm.FileDataUnit;
import dto.dut.comm.HtmlTextDataUnit;
import dto.dut.comm.SimpleTextDataUnit;
import dto.endpoint.Endpoint;
import dto.endpoint.SimpleGroupEndpoint;
import dto.endpoint.SimpleServerEndpoint;
import dto.endpoint.SimpleUserEndpoint;
import dto.json.gson.AlphaGsonConverter;
import dto.json.gson.DefaultGsonAdapter;

import javax.activation.MimeTypeParseException;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class Test {
    public static void main(String[] args) throws MimeTypeParseException {
        //构造json转换器
        AlphaGsonConverter alphaGsonConverter = new AlphaGsonConverter();
        alphaGsonConverter.supportAbsJson(DataUnit.class, new DefaultGsonAdapter<DataUnit>(DataUnit.class));
        alphaGsonConverter.supportAbsJson(Endpoint.class, new DefaultGsonAdapter<Endpoint>(Endpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleTextDataUnit.class, new DefaultGsonAdapter<SimpleTextDataUnit>(SimpleTextDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(HtmlTextDataUnit.class, new DefaultGsonAdapter<HtmlTextDataUnit>(HtmlTextDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(FileDataUnit.class, new DefaultGsonAdapter<FileDataUnit>(FileDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleUserEndpoint.class, new DefaultGsonAdapter<SimpleUserEndpoint>(SimpleUserEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleServerEndpoint.class, new DefaultGsonAdapter<SimpleServerEndpoint>(SimpleServerEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleGroupEndpoint.class, new DefaultGsonAdapter<SimpleGroupEndpoint>(SimpleGroupEndpoint.class));

        ServerProperties serverProperties = new ServerProperties();//配置
        AlphaChatChannelInitializer nettyChannelInitializer = new AlphaChatChannelInitializer();//中间件
        NettyAlphaServer nettyAlphaServer = new NettyAlphaServer(serverProperties, alphaGsonConverter, nettyChannelInitializer);//创建服务器

        nettyAlphaServer.registerService(new DayinService());
        nettyAlphaServer.start();//开启服务器
//        Body body = new Body();
//        body.addDataUnit(new SimpleTextDataUnit("你好棒"));
//        body.addDataUnit(new HtmlTextDataUnit("<h1>标题</h1>"));
//        body.addDataUnit(new FileDataUnit(new FileAttributes("","学习资料","12345Id",156L, new MimeType("application/*")),  null));
//        Alpha alpha = new Alpha(1L, new SimpleUserEndpoint("用户名", "密码"), new SimpleServerEndpoint(),
//                DataType.REQUEST, Action.JOIN_GROUP, BaseProtocol.HTTP, body);
//        Gson gson = new GsonBuilder()
//                .registerTypeHierarchyAdapter(DataUnit.class, new DefaultAdapter<DataUnit>())
//                .registerTypeHierarchyAdapter(Endpoint.class, new DefaultAdapter<Endpoint>())
//                .create();
//
//        String json = gson.toJson(alpha);
//        System.out.println(json);
    }
}
//    Body body = new Body();
//        body.addDataUnit(new SimpleTextDataUnit("你好棒"));
//                body.addDataUnit(new HtmlTextDataUnit("<h1>标题</h1>"));
//                body.addDataUnit(new FileDataUnit(new FileAttributes("","学习资料",156L, new MimeType(MimeTypeData.TEXT_PLAIN)),  null));
//                Alpha alpha = new Alpha(1L, new SimpleUserEndpoint("用户名"), new SimpleServerEndpoint(),
//                DataType.REQUEST, Action.JOIN_GROUP, BaseProtocol.HTTP, body);
