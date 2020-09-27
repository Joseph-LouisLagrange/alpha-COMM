package dto.alphas;

import dto.*;
import dto.dut.action.AdviceDataUnit;
import dto.endpoint.Endpoint;


import java.time.LocalDateTime;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class GenericAlpha {
    public static boolean isHiAlpha(Alpha alpha) {
        return (alpha.getAction() == Action.HI);
    }

    public static Alpha hiAlpha(Alpha helloAlpha) {
        Alpha hiAlpha = new Alpha();
        hiAlpha.setId(helloAlpha.getId());
        hiAlpha.setBaseProtocol(helloAlpha.getBaseProtocol());
        hiAlpha.setDate(LocalDateTime.now());
        hiAlpha.setAction(Action.HI);
        hiAlpha.setFrom(helloAlpha.getTo());
        hiAlpha.setTo(hiAlpha.getFrom());
        return hiAlpha;
    }

    public static boolean isHelloAlpha(Alpha alpha) {
        return alpha.getAction() == Action.HELLO;
    }

    public static Alpha helloAlpha(Alpha hiAlpha) {
        Alpha helloAlpha = new Alpha();
        helloAlpha.setId(hiAlpha.getId());
        helloAlpha.setBaseProtocol(hiAlpha.getBaseProtocol());
        helloAlpha.setDate(LocalDateTime.now());
        helloAlpha.setAction(Action.HI);
        helloAlpha.setFrom(hiAlpha.getTo());
        helloAlpha.setTo(hiAlpha.getFrom());
        return helloAlpha;
    }

    public static Alpha ErrorAlpha(long id, Endpoint from, String error, Endpoint to, BaseProtocol baseProtocol) {
        Alpha errorAlpha = new Alpha();
        errorAlpha.setTo(to);
        errorAlpha.setFrom(from);
        errorAlpha.setAction(Action.ERROR);
        errorAlpha.setDate(LocalDateTime.now());
        errorAlpha.setBaseProtocol(baseProtocol);
        errorAlpha.setId(id);
        errorAlpha.setDataType(DataType.RESPONSE);
        Body body = new Body();
        body.addDataUnit(new AdviceDataUnit(error));
        errorAlpha.setBody(body);
        return errorAlpha;
    }

    protected static Alpha adviceAlpha(BaseProtocol baseProtocol, Endpoint from, Action action, Endpoint to) {
        Alpha alpha = new Alpha();
        alpha.setFrom(from);
        alpha.setTo(to);
        alpha.setDataType(DataType.ADVICE);
        alpha.setBaseProtocol(baseProtocol);
        alpha.setDate(LocalDateTime.now());
        alpha.setAction(action);
        return alpha;
    }

    public static Alpha onlineAdviceAlpha(BaseProtocol baseProtocol, Endpoint from, Endpoint to) {
        return adviceAlpha(baseProtocol, from, Action.ONLINE, to);
    }

    public static Alpha offlineAdviceAlpha(BaseProtocol baseProtocol, Endpoint from, Endpoint to) {
        return adviceAlpha(baseProtocol, from, Action.OFFLINE, to);
    }
}
