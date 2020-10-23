package core.net.netty;

import config.ServerProperties;
import core.net.AlphaNettyNetContext;
import core.net.AlphaServer;
import dto.Alpha;
import dto.endpoint.Endpoint;
import dto.json.AlphaJsonConverter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import service.Service;

import java.net.SocketAddress;
import java.util.*;

/**
 * @author 杨能
 * @create 2020/9/18
 * Netty实现版本的TCP/IP服务器
 */
public class NettyAlphaServer extends AlphaServer {

    protected int bossThead;

    protected int workThead;

    protected AlphaChatChannelInitializer alphaChatChannelInitializer;


    //表示登陆了并且已经通过认证的channel
    protected Set<Map.Entry<Endpoint,Channel>> accessSet = new HashSet<>();

    //表示已连接但是未认证的 channel
    protected Map<SocketAddress,Channel> activeMap=new HashMap<>();

    /**
     *
     * @param serverProperties 服务端配置
     * @param alphaJsonConverter json的转化器
     * @param alphaChatChannelInitializer netty需要使用的 ChannelInitializer
     */
    public NettyAlphaServer(ServerProperties serverProperties, AlphaJsonConverter alphaJsonConverter,AlphaChatChannelInitializer alphaChatChannelInitializer) {
        super(serverProperties, alphaJsonConverter);
        bossThead = serverProperties.getBossThead();
        workThead = serverProperties.getWorkThead();
        this.alphaChatChannelInitializer = alphaChatChannelInitializer;
        //把自己的内核函数主动暴露出去
        this.alphaChatChannelInitializer.setNettyAlphaServer(this);
    }


    public synchronized void active(Channel channel) {
        this.activeMap.put(channel.remoteAddress(),channel);
    }


    private Endpoint getEndpointByChannel(Channel channel){
        return accessSet.stream()
                .filter(endpointChannelEntry -> endpointChannelEntry.getValue().equals(channel))
                .map(Map.Entry::getKey)
                .findAny().orElse(null);
    }

    private Channel getChannelByEndpoint(Endpoint endpoint){
        return accessSet.stream()
                .filter(endpointChannelEntry -> endpointChannelEntry.getKey().equals(endpoint))
                .map(Map.Entry::getValue)
                .findAny().orElse(null);
    }

    @Override
    public void exit(Endpoint endpoint) {
        accessSet.stream()
                .filter(endpointChannelEntry -> endpointChannelEntry.getKey().equals(endpoint))
                .findAny().ifPresent(entry -> accessSet.remove(entry));
    }

    @Override
    public void exit(SocketAddress socketAddress) {
        activeMap.remove(socketAddress);
    }

    @Override
    public boolean isAccess(Endpoint endpoint) {
        Channel channel=getChannelByEndpoint(endpoint);
        return channel!=null;
    }

    @Override
    public boolean isAccess(SocketAddress socketAddress) {
        Channel channel=this.activeMap.get(socketAddress);
        if(channel!=null){
            Endpoint endpoint=getEndpointByChannel(channel);
            return endpoint!=null;
        }
        return false;
    }


    public Endpoint getEndpoint(Channel channel) {
        return getEndpointByChannel(channel);
    }

    private void startNettyServer() {
        /* 主从线程组模型
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThead);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workThead);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .option(ChannelOption.SO_BACKLOG, 200)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(this.alphaChatChannelInitializer);
            ChannelFuture channelFuture = serverBootstrap.bind(super.ip, super.port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();//优雅的关闭主从线程池
        }
    }

    @Override
    public void callService(Alpha alpha, SocketAddress socketAddress) {
        List<Service> serviceList = getServices(alpha);
        for (Service service : serviceList) {
            Channel channel=this.activeMap.get(socketAddress);
            service.run(new AlphaNettyNetContext(channel) ,alpha, this);
        }
    }

    @Override
    public void start() {
        startNettyServer();
    }

    //转发数据包服务（数据重定向）
    @Override
    public void send(Alpha alpha) {
        //获取目标对象
        Endpoint target = alpha.getTo();
        Channel targetChannel=getChannelByEndpoint(target);
        targetChannel.writeAndFlush(alpha);
    }

    @Override
    public void send(Alpha alpha, SocketAddress socketAddress) {
        Channel channel=this.activeMap.get(socketAddress);
        if(channel!=null){
            channel.writeAndFlush(alpha);
        }
    }

    @Override
    public void accessService(SocketAddress socketAddress , Endpoint user) {
        //可接近服务器
        Channel channel=activeMap.get(socketAddress);
        AbstractMap.SimpleEntry<Endpoint,Channel> entry=new AbstractMap.SimpleEntry<Endpoint,Channel>(user,channel);
        accessSet.add(entry);
    }
}
