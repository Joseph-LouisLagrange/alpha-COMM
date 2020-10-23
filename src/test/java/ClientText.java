import client.ClientAlphaGenerator;
import client.ClientProperties;
import core.net.netty.AlphaChatChannelInitializer;
import core.net.netty.FirstInHandler;
import dto.Alpha;
import dto.alphaUtil.GenericAlpha;
import dto.dut.DataUnit;
import dto.dut.comm.FileDataUnit;
import dto.dut.comm.HtmlTextDataUnit;
import dto.dut.comm.SimpleTextDataUnit;
import dto.dut.datatype.ResponseDataUnit;
import dto.dut.safe.BasicAuthenticateDataUnit;
import dto.endpoint.*;
import dto.json.gson.AlphaGsonConverter;
import dto.json.gson.DefaultGsonAdapter;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.jupiter.api.*;


import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 * @author 杨能
 * @create 2020/10/21
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientText {


    @Test
    @Order(1)
    @DisplayName("注册")
    public void register(){
        Alpha alpha=ClientAlphaGenerator.registerBasicAlpha("userNameTest","passwordTest");
    }

    @Test
    @Order(2)
    @DisplayName("登陆")
    public void login(){
        Alpha alpha=ClientAlphaGenerator.loginBasicAlpha("admin1","admin1");
    }

    @Test
    @Order(3)
    @DisplayName("上线")
    public void online() {

    }
}
