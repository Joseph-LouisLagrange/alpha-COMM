package core.net;

import config.ServerProperties;
import core.service.HandleRequestService;
import core.service.HandleResponseService;
import core.service.Service;
import dto.Alpha;
import dto.DataType;
import tool.ReflectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/9/18
 * 一个通用的抽象的上级服务器
 */
public abstract class AlphaServer implements ServiceRegistrar , NetWorker{

    protected int port;

    protected String path;

    public AlphaServer(ServerProperties serverProperties){
        port=serverProperties.getPort();
        this.path=serverProperties.getPath();
    }



    protected List<Service> services=new ArrayList<>();

    protected List<Service> getServices(Alpha alpha){
        return services.stream()
                .filter(service -> {
                    if(ReflectUtil.isExtendClass(service.getClass(),HandleRequestService.class)){
                        return alpha.getDataType().equals(DataType.REQUEST);
                    }
                    if(ReflectUtil.isExtendClass(service.getClass(), HandleResponseService.class)){
                        return alpha.getDataType().equals(DataType.RESPONSE);
                    }
                    return true;
                })
                .filter(service -> service.isSupport(alpha))
                .collect(Collectors.toList());
    }

    public void callService(Alpha alpha){
        List<Service> serviceList=getServices(alpha);
        for(Service service : serviceList){
            service.run(alpha,this);
        }
    }

    public abstract void send(Alpha alpha);

    @Override
    public void register(Service service) {
        services.add(service);
    }
}
