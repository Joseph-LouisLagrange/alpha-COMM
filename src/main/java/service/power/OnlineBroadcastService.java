package service.power;

import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dto.Action;
import dto.Alpha;
import dto.DataType;

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
    public void run(Alpha alpha, AlphaNetWorker alphaNetWorker) {
        this.broadcast(alpha, null, alphaNetWorker);
    }
}
