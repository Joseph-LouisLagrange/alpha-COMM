package core.net.netty;

import config.ServerProperties;
import core.net.AlphaServer;
import dto.Alpha;
import dto.endpoint.Endpoint;
import dto.json.AlphaJsonConverter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 杨能
 * @create 2020/9/18
 * Netty实现版本的TCP/IP服务器
 */
public class NettyAlphaServer extends AlphaServer {

    protected int bossThead;

    protected int workThead;

    protected AlphaChatChannelInitializer nettyChannelInitializer;

    /**
     * 设计这类Map理由：
     * 1.让 ConcurrentHashMap 对抗高并发，频繁的变化
     * 2.  accessChannelMap 来存储已认证连接
     */
    //表示登陆了并且已经通过认证的channel
    protected Map<Endpoint, Channel> accessChannelMap = new HashMap<>();

    //双向map
    protected Map<Channel, Endpoint> accessEndpointMap = new HashMap<>();

    //表示已连接但是未认证的 channel
    protected Map<Endpoint, Channel> activeChannelMap = new HashMap<>();
    //表示已经激活连接的人
    protected Set<Endpoint> activeEndpoints = new HashSet<>();

    public NettyAlphaServer(ServerProperties serverProperties, AlphaJsonConverter alphaJsonConverter, AlphaChatChannelInitializer nettyChannelInitializer) {
        super(serverProperties, alphaJsonConverter);
        bossThead = serverProperties.getBossThead();
        workThead = serverProperties.getWorkThead();
        this.nettyChannelInitializer = nettyChannelInitializer;
        //把自己的内核函数主动暴露出去
        this.nettyChannelInitializer.setNettyAlphaServer(this);
    }


    public synchronized void active(Endpoint endpoint, Channel channel) {
        this.activeEndpoints.add(endpoint);
        activeChannelMap.put(endpoint, channel);
    }

    public boolean isActive(Endpoint endpoint) {
        return this.activeEndpoints.contains(endpoint);
    }

    @Override
    public void accessService(Endpoint endpoint) {
        Channel channel = activeChannelMap.get(endpoint);
        if (endpoint != null) {
            //进去已认证表
            accessChannelMap.put(endpoint, channel);
            accessEndpointMap.put(channel, endpoint);
        }
    }

    @Override
    public void exit(Endpoint endpoint) {
        Channel channel = accessChannelMap.get(endpoint);
        accessEndpointMap.remove(channel);
        accessChannelMap.remove(endpoint);
    }


    public Endpoint getEndpoint(Channel channel) {
        return accessEndpointMap.get(channel);
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
                    .childHandler(this.nettyChannelInitializer);
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
    public void start() {
        startNettyServer();
    }

    //转发数据包服务（数据重定向）
    @Override
    public void send(Alpha alpha) {
        //获取目标对象
        Endpoint target = alpha.getTo();
        activeChannelMap.get(target).writeAndFlush(alpha);
    }
}
