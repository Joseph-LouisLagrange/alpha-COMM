package core.service;

import core.net.AlphaServer;
import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public interface Service {
    public boolean isSupport(Alpha alpha);
    public void run(Alpha alpha , AlphaServer alphaServer);
}
