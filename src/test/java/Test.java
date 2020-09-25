import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.ServerProperties;
import core.net.netty.NettyAlphaServer;

import core.net.netty.AlphaChatChannelInitializer;
import dto.*;
import dto.dut.*;
import dto.endpoint.Endpoint;
import dto.endpoint.SimpleServerEndpoint;
import dto.endpoint.SimpleUserEndpoint;
import dto.json.DefaultAdapter;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class Test {
    public static void main(String[] args) {
//        FastAlphaRepositoryFactory fastAlphaRepositoryFactory=FastAlphaRepositoryFactory.getInstance();
//        AlphaRepository alphaRepository=fastAlphaRepositoryFactory.getEmptyAlphaRepository();
//        alphaRepository.setPrivate(null);
//        SimpleCenterRepository centerRepository=SimpleCenterRepository.getInstance();
//        centerRepository.addAlphaRepository(alphaRepository);
//        System.out.println(centerRepository.getAllAlphaRepository().get(0).toString());
        ServerProperties serverProperties = new ServerProperties();//配置
        AlphaChatChannelInitializer nettyChannelInitializer = new AlphaChatChannelInitializer();//中间件
        NettyAlphaServer nettyAlphaServer = new NettyAlphaServer(serverProperties, nettyChannelInitializer);//创建服务器
        //nettyAlphaServer.start();//开启服务器
        Body body = new Body();
        body.addDataUnit(new SimpleTextDataUnit("你好棒"));
        body.addDataUnit(new HtmlTextDataUnit("<h1>标题</h1>"));
        body.addDataUnit(new FileDataUnit(null, ContentType.APPLICATION_JSON, null));
        Alpha alpha = new Alpha(1L, new SimpleUserEndpoint("用户名", "密码"), new SimpleServerEndpoint(),
                DataType.REQUEST, Action.JOIN_GROUP, BaseProtocol.HTTP, body);
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(DataUnit.class, new DefaultAdapter<DataUnit>())
                .registerTypeHierarchyAdapter(Endpoint.class, new DefaultAdapter<Endpoint>())
                .create();
        System.out.println(alpha.getFrom());
        String json = gson.toJson(alpha);
        Alpha alpha1 = gson.fromJson(json, Alpha.class);
        System.out.println(alpha1.getFrom().getTypeKey());
    }
}
