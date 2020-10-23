package service.base;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dao.UserRepository;
import dto.Action;
import dto.Alpha;
import dto.alphaUtil.GenericAlpha;
import dto.dut.DataUnit;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.Endpoint;
import dto.endpoint.SimpleServerEndpoint;
import dto.endpoint.SimpleUserEndpoint;
import service.HandleRequestService;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class LoginService extends HandleRequestService {

    private UserRepository userRepository=null;
    public LoginService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getAction() == Action.LOGIN;
    }

    @Override
    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker) {
        Endpoint endpointFrom = null;
        List<DataUnit> dataUnitList = alpha.getBody();
        Alpha backAlpha=null;
        if (dataUnitList.size()<1) {
            backAlpha = GenericAlpha.formatErrorResponseAlpha(alpha,alphaNetWorker.toJson(alpha));
        } else {
            BasicAuthenticateDataUnit basicAuthenticateDataUnit = (BasicAuthenticateDataUnit) dataUnitList.get(0);
            if(this.userRepository.login(basicAuthenticateDataUnit)){
                 backAlpha=GenericAlpha.successResponseAlpha(alpha);
                 Endpoint endpoint=new SimpleUserEndpoint(basicAuthenticateDataUnit.getUserName());
                 backAlpha.setTo(endpoint);
                 alphaNetWorker.accessService(ctx.getSocketAddress(),endpoint);
            }else{
                 backAlpha=GenericAlpha.failResponseAlpha(alpha,"[登陆失败]");
            }
        }
        alphaNetWorker.send(backAlpha,ctx.getSocketAddress());
    }
}
