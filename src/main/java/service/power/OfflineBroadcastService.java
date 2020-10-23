package service.power;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.endpoint.Endpoint;

import java.util.HashSet;

/**
 * 离线广播服务
 * @author 杨能
 * @create 2020/10/22
 */
public class OfflineBroadcastService extends BroadcastService{

    CenterRepository centerRepository=null;
    public OfflineBroadcastService(CenterRepository centerRepository){
        this.centerRepository=centerRepository;
    }
    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getDataType()== DataType.ADVICE
                &&
                alpha.getAction()== Action.OFFLINE;
    }

    @Override
    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker) {
        System.out.println("离线广播");
        this.broadcast(alpha,new HashSet<>(),alphaNetWorker);
    }
}
