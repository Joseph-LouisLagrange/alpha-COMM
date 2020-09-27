package service;

import core.net.AlphaNetWorker;
import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class DayinService implements Service {

    @Override
    public boolean isSupport(Alpha alpha) {
        return true;
    }

    @Override
    public void run(Alpha alpha, AlphaNetWorker alphaNetWorker) {
        //alphaNetWorker.send(alpha);
        System.out.println(alpha);
    }
}
