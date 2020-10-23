package core.net;

import dto.Alpha;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.Endpoint;
import io.netty.channel.Channel;

import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

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


    public void send(Alpha alpha, SocketAddress socketAddress);

    /**
     * 对这个SocketAddress提供合法的服务
     *
     * @param socketAddress 源 SocketAddress
     */
    public void accessService(SocketAddress socketAddress , Endpoint user);


    /**
     * 退出服务器 ，不能使用服务器功能
     *
     * @param endpoint 源
     */
    public void exit(Endpoint endpoint);


    public void exit(SocketAddress socketAddress);

    /**
     * json化
     * @param alpha 数据包
     * @return json
     */
    public String toJson(Alpha alpha);

    /**
     * 判断连接通道
     * @param endpoint 目标
     * @return true|false
     */
    public boolean isAccess(Endpoint endpoint);


    public boolean isAccess(SocketAddress socketAddress);
}
