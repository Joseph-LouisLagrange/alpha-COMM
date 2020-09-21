package core.net.netty;

import config.ServerProperties;
import core.net.AlphaServer;
import dto.Alpha;
import dto.Endpoint;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;
import java.util.Map;
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

    protected Map<Endpoint, Channel> channelMap = new ConcurrentHashMap<>();

    //这里可能会出现线程不安全(可以后面换成轻量级的HashTable)
    protected Map<Endpoint, SocketChannel> socketChannelMap = new HashMap<>();

    public NettyAlphaServer(ServerProperties serverProperties, AlphaChatChannelInitializer nettyChannelInitializer) {
        super(serverProperties);
        bossThead = serverProperties.getBossThead();
        workThead = serverProperties.getWorkThead();
        this.nettyChannelInitializer = nettyChannelInitializer;
        //把自己的内核函数主动暴露出去
        this.nettyChannelInitializer.setNettyAlphaServer(this);
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

    @Override
    public void send(Alpha alpha) {
        //获取目标对象
        Endpoint target = alpha.getTo();
        socketChannelMap.get(target).writeAndFlush(alpha);
    }
}
