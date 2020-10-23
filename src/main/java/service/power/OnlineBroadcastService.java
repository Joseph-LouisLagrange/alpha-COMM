package service.power;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.endpoint.Endpoint;
import dto.endpoint.SimpleUserEndpoint;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class OnlineBroadcastService extends BroadcastService {
    protected CenterRepository centerRepository;

    public OnlineBroadcastService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getDataType() == DataType.ADVICE
                && alpha.getAction() == Action.ONLINE;
    }

    @Override
    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker) {
        Set<Endpoint> endpoints=new HashSet<>();
        System.out.println("上线广播");
        this.broadcast(alpha, endpoints, alphaNetWorker);
    }
}
