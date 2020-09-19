package config;

import io.netty.buffer.ByteBuf;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class ServerProperties {
    //端口，默认为8081
    int port=8081;
    //WebSocket所处路径
    String path="/alpha";

    String ip="127.0.0.1";

    NettyProperties nettyProperties=new NettyProperties();

    static class NettyProperties{
         int bossThead=1;
         int workThead=Runtime.getRuntime().availableProcessors()*2;

        public int getBossThead() {
            return bossThead;
        }

        public void setBossThead(int bossThead) {
            this.bossThead = bossThead;
        }

        public int getWorkThead() {
            return workThead;
        }

        public void setWorkThead(int workThead) {
            this.workThead = workThead;
        }
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getBossThead() {
        return nettyProperties.getBossThead();
    }

    public void setBossThead(int bossThead) {
        nettyProperties.setBossThead(bossThead);
    }

    public int getWorkThead() {
        return nettyProperties.getWorkThead();
    }

    public void setWorkThead(int workThead) {
        nettyProperties.setWorkThead(workThead);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

