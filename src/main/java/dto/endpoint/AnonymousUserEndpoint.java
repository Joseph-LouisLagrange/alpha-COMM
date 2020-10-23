package dto.endpoint;

/**
 * @author 杨能
 * @create 2020/10/22
 */
public class AnonymousUserEndpoint extends Endpoint {
    private String netId = null;

    public AnonymousUserEndpoint(){
        this("127.127.127.127",0);
    }

    public AnonymousUserEndpoint(String host,int port){
        this.setNetId(host+":"+port);
    }

    @Override
    public String getTypeKey() {
        return "AnonymousUserEndpoint";
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }
}
