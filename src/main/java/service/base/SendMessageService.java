package service.base;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.alphaUtil.GenericAlpha;
import dto.endpoint.Endpoint;
import service.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨能
 * @create 2020/10/1
 */
public class SendMessageService implements Service {


    private CenterRepository centerRepository=null;

    public SendMessageService(CenterRepository centerRepository){
        this.centerRepository=centerRepository;
    }
    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getDataType()== DataType.REQUEST
                && alpha.getAction()== Action.SEND_MESSAGE;
    }

    @Override
    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker) {
        //存入未读消息中
        centerRepository.addUnread(alpha.getTo(),alpha.clone());
        //回送消息
        alphaNetWorker.send(GenericAlpha.successResponseAlpha(alpha));
        //判断是否在离线状态
        if(alphaNetWorker.isAccess(alpha.getTo())){
            //在线，直接发出去
            alphaNetWorker.send(alpha.clone());
        }
    }
}
