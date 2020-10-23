package core.net;

import config.ServerProperties;
import service.HandleRequestService;
import service.HandleResponseService;
import service.Service;
import dto.Alpha;
import dto.DataType;
import dto.json.AlphaJsonConverter;
import tool.ReflectUtil;

import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/9/18
 * 一个通用的抽象的上级服务器
 */
public abstract class AlphaServer implements Registrar, AlphaNetWorker {

    protected int port;

    protected String ip;

    protected AlphaJsonConverter alphaJsonConverter;

    public static Charset charset;

    public AlphaServer(ServerProperties serverProperties, AlphaJsonConverter alphaJsonConverter) {
        port = serverProperties.getPort();
        this.ip = serverProperties.getIp();
        this.alphaJsonConverter = alphaJsonConverter;
        charset = serverProperties.getCharset();
    }

    protected List<Service> services = new ArrayList<>();


    protected List<Service> getServices(Alpha alpha) {
        return services.stream()
                .filter(service -> {
                    if (ReflectUtil.isExtendClass(service.getClass(), HandleRequestService.class)) {
                        return alpha.getDataType().equals(DataType.REQUEST);
                    }
                    if (ReflectUtil.isExtendClass(service.getClass(), HandleResponseService.class)) {
                        return alpha.getDataType().equals(DataType.RESPONSE);
                    }
                    return true;
                })
                .filter(service -> service.isSupport(alpha))
                .collect(Collectors.toList());
    }

    public abstract void callService(Alpha alpha, SocketAddress socketAddress) ;

    public abstract void start();


    @Override
    public String toJson(Alpha alpha) {
        return this.alphaJsonConverter.toJson(alpha);
    }

    @Override
    public void registerAlphaJsonConverter(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }

    @Override
    public void registerService(Service service) {
        services.add(service);
    }

    public AlphaJsonConverter getAlphaJsonConverter() {
        return alphaJsonConverter;
    }

    public void setAlphaJsonConverter(AlphaJsonConverter alphaJsonConverter) {
        this.alphaJsonConverter = alphaJsonConverter;
    }
}
