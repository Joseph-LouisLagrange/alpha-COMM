package client;

import dto.*;
import dto.dut.AdviceDataUnit;
import dto.dut.DataUnit;
import dto.dut.comm.SimpleTextDataUnit;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.AnonymousUserEndpoint;
import dto.endpoint.Endpoint;
import dto.endpoint.SimpleServerEndpoint;
import dto.endpoint.SimpleUserEndpoint;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 客户端的alpha数据包的生成器
 * @author 杨能
 * @create 2020/10/21
 */
public class ClientAlphaGenerator {
    public static final ClientProperties clientProperties;
    static {
        //加载配置
        clientProperties=ClientProperties.getInstance();
    }
    //暂时不用考虑集群
    public static final Endpoint server=new SimpleServerEndpoint();

    //默认为匿名用户
    public static Endpoint user=new AnonymousUserEndpoint();

    public static Random random=new Random(System.currentTimeMillis());

    public static Endpoint getUser() {
        return user;
    }

    public static void setUser(Endpoint user) {
        ClientAlphaGenerator.user = user;
    }

    public synchronized static long randomId(){
        return random.nextLong();
    }

    public static Alpha onlineAdviceAlpha(){
        Alpha alpha=new Alpha(randomId());
        alpha.setTo(server);
        alpha.setFrom(getUser());
        alpha.addDataUnit(new AdviceDataUnit("[我上线了]"));
        alpha.setAction(Action.ONLINE);
        alpha.setDataType(DataType.ADVICE);
        alpha.setBaseProtocol(BaseProtocol.TCP);
        alpha.setDateTime(LocalDateTime.now());
        return alpha;
    }

    public static Alpha offlineAdviceAlpha(){
        Alpha alpha=new Alpha(randomId());
        alpha.setTo(server);
        alpha.setFrom(getUser());
        alpha.addDataUnit(new AdviceDataUnit("[我离线了]"));
        alpha.setAction(Action.OFFLINE);
        alpha.setDataType(DataType.ADVICE);
        alpha.setBaseProtocol(BaseProtocol.TCP);
        alpha.setDateTime(LocalDateTime.now());
        return alpha;
    }

    public static Alpha loginBasicAlpha(String userName,String password){
        BasicAuthenticateDataUnit basicAuthenticateDataUnit=new BasicAuthenticateDataUnit(userName,password);
        Alpha alpha=new Alpha(randomId());
        alpha.setTo(server);
        alpha.setFrom(getUser());
        alpha.addDataUnit(basicAuthenticateDataUnit);
        alpha.setAction(Action.LOGIN);
        alpha.setDataType(DataType.REQUEST);
        alpha.setBaseProtocol(BaseProtocol.TCP);
        alpha.setDateTime(LocalDateTime.now());
        return alpha;
    }

    public static Alpha registerBasicAlpha(String userName,String password){
        BasicAuthenticateDataUnit basicAuthenticateDataUnit=new BasicAuthenticateDataUnit(userName,password);
        Alpha alpha=new Alpha(randomId());
        alpha.setTo(server);
        alpha.setFrom(getUser());
        alpha.addDataUnit(basicAuthenticateDataUnit);
        alpha.setAction(Action.REGISTER);
        alpha.setDataType(DataType.REQUEST);
        alpha.setBaseProtocol(BaseProtocol.TCP);
        alpha.setDateTime(LocalDateTime.now());
        return alpha;
    }

    public static Alpha sendMessageAlpha(Endpoint to , List<DataUnit> body){
        Alpha alpha=new Alpha(randomId());
        alpha.setTo(to);
        alpha.setBaseProtocol(BaseProtocol.TCP);
        alpha.setDataType(DataType.REQUEST);
        alpha.setAction(Action.SEND_MESSAGE);
        alpha.setFrom(getUser());
        alpha.setDateTime(LocalDateTime.now());
        alpha.setBody(body);
        return alpha;
    }

    public static Alpha sendSimpleTextAlpha(Endpoint to, String text){
        return sendMessageAlpha(to, Collections.singletonList(new SimpleTextDataUnit(text)));
    }

}
