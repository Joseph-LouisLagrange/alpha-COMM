package core.net.netty;

import core.net.AlphaServer;
import dto.Alpha;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author 杨能
 * @create 2020/9/18
 * Netty实现版本的TCP/IP服务器
 */
public class NettyAlphaServer extends AlphaServer{
    public NettyAlphaServer(){
        /* 主从线程组模型
         */
        EventLoopGroup bossGroup =new NioEventLoopGroup(1);
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap =new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new  ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                        }
                    });
            ChannelFuture channelFuture= serverBootstrap.bind(super.port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();//优雅的关闭主从线程池
        }
    }


    @Override
    public void send(Alpha alpha) {

    }
}
