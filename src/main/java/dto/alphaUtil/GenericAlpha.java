package dto.alphaUtil;

import dto.*;
import dto.dut.datatype.ErrorResponseDataUnit;
import dto.dut.datatype.FailResponseDataUnit;
import dto.dut.datatype.ResponseDataUnit;
import dto.dut.datatype.SuccessResponseDataUnit;
import dto.endpoint.Endpoint;


import java.time.LocalDateTime;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class GenericAlpha {

    private static void toSwFrom(Alpha alpha){
        Endpoint endpoint=alpha.getFrom();
        alpha.setFrom(alpha.getTo());
        alpha.setTo(endpoint);
    }

    public static boolean isHiAlpha(Alpha alpha) {
        return (alpha.getAction() == Action.HI);
    }

    public static Alpha hiAlpha(Alpha helloAlpha) {
        Alpha hiAlpha = helloAlpha.alphaMetaClone();
        toSwFrom(hiAlpha);
        hiAlpha.setDateTime(LocalDateTime.now());
        hiAlpha.setAction(Action.HI);
        return hiAlpha;
    }

    public static boolean isHelloAlpha(Alpha alpha) {
        return alpha.getAction() == Action.HELLO;
    }

    public static Alpha helloAlpha(Alpha hiAlpha) {
        Alpha helloAlpha = hiAlpha.alphaMetaClone();
        toSwFrom(helloAlpha);
        helloAlpha.setDateTime(LocalDateTime.now());
        helloAlpha.setAction(Action.HELLO);
        return helloAlpha;
    }

    public static Alpha errorResponseAlpha(Alpha alpha,String error) {
        Alpha errorAlpha = alpha.alphaMetaClone();
        errorAlpha.setDateTime(LocalDateTime.now());
        errorAlpha.setDataType(DataType.RESPONSE);
        errorAlpha.addDataUnit(new ErrorResponseDataUnit(error));
        return errorAlpha;
    }

    public static Alpha formatErrorResponseAlpha(Alpha alpha,String jsonAlpha){
        return errorResponseAlpha(alpha,"[格式异常] "+jsonAlpha);
    }

    public static Alpha notCertifiedResponseAlpha(Alpha alpha){
        return failResponseAlpha(alpha,"[未认证]");
    }

    protected static Alpha adviceAlpha(BaseProtocol baseProtocol, Endpoint from, Action action, Endpoint to) {
        Alpha alpha = new Alpha();
        alpha.setFrom(from);
        alpha.setTo(to);
        alpha.setDataType(DataType.ADVICE);
        alpha.setBaseProtocol(baseProtocol);
        alpha.setDateTime(LocalDateTime.now());
        alpha.setAction(action);
        return alpha;
    }

    public static Alpha onlineAdviceAlpha(BaseProtocol baseProtocol, Endpoint from, Endpoint to) {
        return adviceAlpha(baseProtocol, from, Action.ONLINE, to);
    }

    public static Alpha offlineAdviceAlpha(BaseProtocol baseProtocol, Endpoint from, Endpoint to) {
        return adviceAlpha(baseProtocol, from, Action.OFFLINE, to);
    }

    public static Alpha responseAlpha(Alpha requestAlpha, String message){
        Alpha alpha=requestAlpha.alphaMetaClone();
        toSwFrom(alpha);
        alpha.setDataType(DataType.RESPONSE);
        alpha.setDateTime(LocalDateTime.now());
        alpha.addDataUnit(new ResponseDataUnit(message));
        return alpha;
    }

    public static Alpha successResponseAlpha(Alpha requestAlpha){
        Alpha alpha=requestAlpha.alphaMetaClone();
        toSwFrom(alpha);
        alpha.setDataType(DataType.RESPONSE);
        alpha.setDateTime(LocalDateTime.now());
        alpha.addDataUnit(new SuccessResponseDataUnit());
        return alpha;
    }
    public static Alpha failResponseAlpha(Alpha requestAlpha,String reason){
        Alpha alpha=requestAlpha.alphaMetaClone();
        toSwFrom(alpha);
        alpha.setDataType(DataType.RESPONSE);
        alpha.setDateTime(LocalDateTime.now());
        alpha.addDataUnit(new FailResponseDataUnit(reason));
        return alpha;
    }


}
