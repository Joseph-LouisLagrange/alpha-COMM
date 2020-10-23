package test;

import client.*;
import core.net.netty.FirstInHandler;
import dto.dut.AdviceDataUnit;
import dto.dut.DataUnit;
import dto.dut.comm.FileDataUnit;
import dto.dut.comm.HtmlTextDataUnit;
import dto.dut.comm.SimpleTextDataUnit;
import dto.dut.datatype.FailResponseDataUnit;
import dto.dut.datatype.ResponseDataUnit;
import dto.dut.datatype.SuccessResponseDataUnit;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.*;
import dto.json.gson.AlphaGsonConverter;
import dto.json.gson.DefaultGsonAdapter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 杨能
 * @create 2020/10/22
 */
public class TestChain {
    static List<TestBody> list=new ArrayList<>();

    static AlphaGsonConverter alphaGsonConverter = new AlphaGsonConverter();


    static ClientProperties clientProperties=ClientProperties.getInstance();

    static Channel channel;

    public static void startServer(){
        alphaGsonConverter.supportAbsJson(DataUnit.class, new DefaultGsonAdapter<DataUnit>(DataUnit.class));
        alphaGsonConverter.supportAbsJson(Endpoint.class, new DefaultGsonAdapter<Endpoint>(Endpoint.class));

        //注册合法的抽象json转化
        alphaGsonConverter.registerTypeHierarchyAdapter(BasicAuthenticateDataUnit.class,new DefaultGsonAdapter<BasicAuthenticateDataUnit>(BasicAuthenticateDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleTextDataUnit.class, new DefaultGsonAdapter<SimpleTextDataUnit>(SimpleTextDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(HtmlTextDataUnit.class, new DefaultGsonAdapter<HtmlTextDataUnit>(HtmlTextDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(FileDataUnit.class, new DefaultGsonAdapter<FileDataUnit>(FileDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(ResponseDataUnit.class,new DefaultGsonAdapter<ResponseDataUnit>(ResponseDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SuccessResponseDataUnit.class,new DefaultGsonAdapter<SuccessResponseDataUnit>(SuccessResponseDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(FailResponseDataUnit.class,new DefaultGsonAdapter<FailResponseDataUnit>(FailResponseDataUnit.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(AdviceDataUnit.class,new DefaultGsonAdapter<AdviceDataUnit>(AdviceDataUnit.class));

        //注册合法的抽象json转化
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleUserEndpoint.class, new DefaultGsonAdapter<SimpleUserEndpoint>(SimpleUserEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleServerEndpoint.class, new DefaultGsonAdapter<SimpleServerEndpoint>(SimpleServerEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(SimpleGroupEndpoint.class, new DefaultGsonAdapter<SimpleGroupEndpoint>(SimpleGroupEndpoint.class));
        alphaGsonConverter.registerTypeHierarchyAdapter(AnonymousUserEndpoint.class,new DefaultGsonAdapter<AnonymousUserEndpoint>(AnonymousUserEndpoint.class));

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new StringDecoder())
                                    .addLast(new AlphaInHandler(alphaGsonConverter))
                                    .addLast(new AlphaCenter());
                            ch.pipeline()
                                    .addLast(new StringEncoder())
                                    .addLast(new AlphaOutHandler(alphaGsonConverter));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(clientProperties.getServerInetSocketAddress()).sync();
            channel=channelFuture.channel();
//            String register=alphaGsonConverter.toJson(ClientAlphaGenerator.registerBasicAlpha("userNameTest","passwordTest"));
//            channel.writeAndFlush(register);
            Scanner scanner=new Scanner(System.in);
            while (true){
                int select=scanner.nextInt();
                System.out.println(select);
                if(select==0)
                    break;
                switch (select){
                    case 1: channel.writeAndFlush(ClientAlphaGenerator.registerBasicAlpha(scanner.next(),scanner.next()));break;
                    case 2: channel.writeAndFlush(ClientAlphaGenerator.loginBasicAlpha(scanner.next(),scanner.next()));ClientAlphaGenerator.setUser(new SimpleUserEndpoint(scanner.next()));break;
                    case 3: channel.writeAndFlush(ClientAlphaGenerator.onlineAdviceAlpha());break;
                    case 4: channel.writeAndFlush(ClientAlphaGenerator.offlineAdviceAlpha());break;
                    case 5: channel.writeAndFlush(ClientAlphaGenerator.sendSimpleTextAlpha(new SimpleUserEndpoint(scanner.next()),"你好啊！！！"));break;
                }
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        startServer();

    }
}


