package client;

import java.net.InetSocketAddress;
import java.util.Properties;

/**
 * @author 杨能
 * @create 2020/10/22
 */
public class ClientProperties {
      Properties properties;

      String localIp = "127.0.0.1";
      String serverIp = "127.0.0.1";
      int localPort = 8888;
      int serverPort = 8081;

      public static ClientProperties clientProperties=null;



      public static ClientProperties getInstance(){
            if(clientProperties==null){
                  //这里要注入 Properties
                  clientProperties=new ClientProperties();
            }
            return clientProperties;
      }

      private ClientProperties(){}

      private ClientProperties(Properties properties){
            this.properties=properties;
      }

      public InetSocketAddress getServerInetSocketAddress(){
            return new InetSocketAddress(serverIp,serverPort);
      }

      public InetSocketAddress getLocalInetAddress(){
            return new InetSocketAddress(localIp,localPort);
      }

      public String getLocalIp() {
            return localIp;
      }

      public void setLocalIp(String localIp) {
            this.localIp = localIp;
      }

      public String getServerIp() {
            return serverIp;
      }

      public void setServerIp(String serverIp) {
            this.serverIp = serverIp;
      }

      public int getLocalPort() {
            return localPort;
      }

      public void setLocalPort(int localPort) {
            this.localPort = localPort;
      }

      public int getServerPort() {
            return serverPort;
      }

      public void setServerPort(int serverPort) {
            this.serverPort = serverPort;
      }
}
