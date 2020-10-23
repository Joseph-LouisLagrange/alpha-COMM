package service.base;

import core.net.AlphaNetContext;
import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dao.UserRepository;
import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.alphaUtil.GenericAlpha;
import dto.dut.safe.BasicAuthenticateDataUnit;
import service.Service;

/**
 * 注册服务
 * @author 杨能
 * @create 2020/10/1
 */
public class RegisterService implements Service {

    protected UserRepository userRepository;

    protected CenterRepository centerRepository;

    public RegisterService(UserRepository userRepository, CenterRepository centerRepository){
        this.userRepository=userRepository;
        this.centerRepository=centerRepository;
    }

    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getDataType()== DataType.REQUEST
                &&
                alpha.getAction() == Action.REGISTER;
    }

    @Override
    public void run(AlphaNetContext ctx , Alpha alpha, AlphaNetWorker alphaNetWorker) {
        Alpha backAlpha=null;
        if(alpha.getBody().size()<1){
            backAlpha= GenericAlpha.formatErrorResponseAlpha(alpha,alphaNetWorker.toJson(alpha));
        }else{
            BasicAuthenticateDataUnit basicAuthenticateDataUnit= (BasicAuthenticateDataUnit) alpha.getBody().get(0);
            if(this.userRepository.register(basicAuthenticateDataUnit)){
                centerRepository.registerUser(alpha.getFrom());
                backAlpha=GenericAlpha.successResponseAlpha(alpha);
                //给用户分配相关资源
            }else{
                backAlpha=GenericAlpha.failResponseAlpha(alpha,"[注册失败]");
            }
        }
        alphaNetWorker.send(backAlpha,ctx.getSocketAddress());
    }
}
