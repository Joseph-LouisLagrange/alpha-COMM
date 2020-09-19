package core.service;

import core.net.AlphaServer;
import dto.Action;
import dto.Alpha;
import dto.DataType;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class DayinService implements Service {
    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getAction().equals(Action.ADD_FRIEND);
    }

    @Override
    public void run(Alpha alpha , AlphaServer alphaServer) {
        System.out.println(alpha.getBody().toString());
    }
}
