package service;

import core.net.AlphaNetWorker;
import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class SendMessageService implements Service {

    @Override
    public boolean isSupport(Alpha alpha) {
        return false;
    }

    @Override
    public void run(Alpha alpha, AlphaNetWorker alphaNetWorker) {

    }
}
