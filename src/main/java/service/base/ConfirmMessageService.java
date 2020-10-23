package service.base;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dto.Action;
import dto.Alpha;
import dto.DataType;
import service.Service;

/**
 * @author 杨能
 * @create 2020/10/2
 */
public class ConfirmMessageService implements Service {

    protected CenterRepository centerRepository;

    public ConfirmMessageService(CenterRepository centerRepository){
        this.centerRepository=centerRepository;
    }

    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getDataType()== DataType.RESPONSE
                && alpha.getAction()== Action.SEND_MESSAGE;
    }

    @Override
    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker) {
        Alpha a=centerRepository.popUnreadAlpha(alpha.getFrom(),alpha.getId());
    }
}
