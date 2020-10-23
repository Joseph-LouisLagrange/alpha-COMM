package service.power;

import core.net.AlphaNetWorker;
import dto.Alpha;
import dto.endpoint.Endpoint;
import service.Service;

import java.util.Set;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public abstract class BroadcastService implements Service {
    protected void broadcast(Alpha alpha, Set<Endpoint> toEndpoints, AlphaNetWorker alphaNetWorker) {
        for (Endpoint endpoint : toEndpoints) {
            Alpha newAlpha = alpha.alphaMetaClone();
            newAlpha.setTo(endpoint);
            alphaNetWorker.send(newAlpha);
        }
    }
}
