import config.ServerProperties;
import core.net.netty.AlphaChatChannelInitializer;
import core.net.netty.NettyAlphaServer;

import core.net.netty.AlphaChatHttp_WSChannelInitializer;
import core.net.netty.TcpAlphaChatChannelInitializer;
import dao.CenterRepository;
import dao.InMemoryCenterRepository;
import dao.InMemoryUserRepository;
import dao.UserRepository;
import dto.dut.AdviceDataUnit;
import dto.dut.datatype.FailResponseDataUnit;
import dto.dut.datatype.ResponseDataUnit;
import dto.dut.datatype.SuccessResponseDataUnit;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.dut.DataUnit;
import dto.dut.comm.FileDataUnit;
import dto.dut.comm.HtmlTextDataUnit;
import dto.dut.comm.SimpleTextDataUnit;
import dto.endpoint.*;
import dto.json.gson.AlphaGsonConverter;
import dto.json.gson.DefaultGsonAdapter;


import service.base.LoginService;
import service.base.RegisterService;
import service.base.SendMessageService;
import service.power.OfflineBroadcastService;
import service.power.OnlineBroadcastService;

import javax.activation.MimeTypeParseException;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class ServerTest {

    public static void main(String[] args) throws MimeTypeParseException {

        UserRepository userRepository= InMemoryUserRepository.getInstance();

        CenterRepository centerRepository=InMemoryCenterRepository.getInstance();

        //构造json转换器
        AlphaGsonConverter alphaGsonConverter = new AlphaGsonConverter();
        alphaGsonConverter.supportAbsJson(DataUnit.class, new DefaultGsonAdapter<DataUnit>(DataUnit.class));
        alphaGsonConverter.supportAbsJson(Endpoint.class, new DefaultGsonAdapter<Endpoint>(Endpoint.class));

        //注册合法的抽象json转化
        alphaGsonConverter.registerTypeHierarchyAdapter(BasicAuthenticateDataUnit.class,new DefaultGsonAdapter<BasicAuthenticateDataUnit>(BasicAuthenticateDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleTextDataUnit.class, new DefaultGsonAdapter<SimpleTextDataUnit>(SimpleTextDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(HtmlTextDataUnit.class, new DefaultGsonAdapter<HtmlTextDataUnit>(HtmlTextDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(FileDataUnit.class, new DefaultGsonAdapter<FileDataUnit>(FileDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(ResponseDataUnit.class,new DefaultGsonAdapter<ResponseDataUnit>(ResponseDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SuccessResponseDataUnit.class,new DefaultGsonAdapter<SuccessResponseDataUnit>(SuccessResponseDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(FailResponseDataUnit.class,new DefaultGsonAdapter<FailResponseDataUnit>(FailResponseDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(AdviceDataUnit.class,new DefaultGsonAdapter<AdviceDataUnit>(AdviceDataUnit.class));


        //注册合法的抽象json转化
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleUserEndpoint.class, new DefaultGsonAdapter<SimpleUserEndpoint>(SimpleUserEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleServerEndpoint.class, new DefaultGsonAdapter<SimpleServerEndpoint>(SimpleServerEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleGroupEndpoint.class, new DefaultGsonAdapter<SimpleGroupEndpoint>(SimpleGroupEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(AnonymousUserEndpoint.class,new DefaultGsonAdapter<AnonymousUserEndpoint>(AnonymousUserEndpoint.class));

        ServerProperties serverProperties = new ServerProperties();//配置
        AlphaChatChannelInitializer alphaChatChannelInitializer=new TcpAlphaChatChannelInitializer();
        NettyAlphaServer nettyAlphaServer = new NettyAlphaServer(serverProperties, alphaGsonConverter, alphaChatChannelInitializer);//创建服务器

        //挂载服务
        nettyAlphaServer.registerService(new RegisterService(userRepository,centerRepository));
        nettyAlphaServer.registerService(new LoginService(userRepository));
        nettyAlphaServer.registerService(new OnlineBroadcastService(centerRepository));
        nettyAlphaServer.registerService(new OfflineBroadcastService(centerRepository));
        nettyAlphaServer.registerService(new SendMessageService(centerRepository));
        nettyAlphaServer.start();//开启服务器
    }
}
