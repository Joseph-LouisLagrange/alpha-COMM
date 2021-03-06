package service;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public interface Service {
    public boolean isSupport(Alpha alpha);

    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker);
}
