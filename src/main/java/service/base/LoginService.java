package service.base;

import core.net.AlphaNetWorker;
import dao.CenterRepository;
import dto.Action;
import dto.Alpha;
import dto.alphas.GenericAlpha;
import dto.dut.DataUnit;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.Endpoint;
import dto.endpoint.SimpleServerEndpoint;
import service.HandleRequestService;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class LoginService extends HandleRequestService {

    protected CenterRepository centerRepository;

    public LoginService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public boolean isSupport(Alpha alpha) {
        return alpha.getAction() == Action.LOGIN;
    }

    @Override
    public void run(Alpha alpha, AlphaNetWorker alphaNetWorker) {
        Endpoint endpointFrom = alpha.getFrom();
        List<DataUnit> dataUnitList = alpha.getBody().getDataUnitList();
        if (dataUnitList.size() != 1 || (!(dataUnitList.get(0) instanceof BasicAuthenticateDataUnit))) {
            Alpha backAlpha = GenericAlpha.ErrorAlpha(alpha.getId(), new SimpleServerEndpoint(), "未提供认证信息", endpointFrom, alpha.getBaseProtocol());
            alphaNetWorker.send(backAlpha);
        } else {
            BasicAuthenticateDataUnit basicAuthenticateDataUnit = (BasicAuthenticateDataUnit) dataUnitList.get(0);

        }
    }
}
