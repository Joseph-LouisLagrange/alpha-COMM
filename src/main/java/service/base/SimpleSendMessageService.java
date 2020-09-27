package service.base;

import core.net.AlphaNetWorker;
import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class SimpleSendMessageService extends SendMessageService{
    @Override
    public boolean isSupport(Alpha alpha) {
        return false;
    }

    @Override
    public void run(Alpha alpha, AlphaNetWorker alphaNetWorker) {

    }


}
