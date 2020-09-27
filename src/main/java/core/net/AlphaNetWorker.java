package core.net;

import dto.Alpha;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.Endpoint;

/**
 * @author 杨能
 * @create 2020/9/19
 * 为上级服务提供的接口
 */
public interface AlphaNetWorker {
    /**
     * 发送数据包
     *
     * @param alpha 预传送的数据包
     */
    public void send(Alpha alpha);

    /**
     * 允许服务器为 endpoint 提供服务
     *
     * @param endpoint 源
     */
    public void accessService(Endpoint endpoint);

    /**
     * 退出服务器 ，不能使用服务器功能
     *
     * @param endpoint 源
     */
    public void exit(Endpoint endpoint);
}
