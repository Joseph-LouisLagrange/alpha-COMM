package dto.endpoint;

import java.util.Objects;

/**
 * 内置的默认实现
 */
public class SimpleUserEndpoint extends Endpoint {
    private String userName;

    public SimpleUserEndpoint() {

    }

    public SimpleUserEndpoint(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUserEndpoint that = (SimpleUserEndpoint) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String getTypeKey() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "SimpleUserEndpoint{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
