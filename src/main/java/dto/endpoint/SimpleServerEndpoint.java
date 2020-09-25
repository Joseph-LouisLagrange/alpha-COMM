package dto.endpoint;

/**
 * 单机的服务服务器
 */
public class SimpleServerEndpoint extends Endpoint {

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getTypeKey() {
        return this.getClass().getSimpleName();
    }
}
