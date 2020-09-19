package dto;

import java.util.Objects;

/**
 * 内置的默认实现
 */
public class PersonEndPoint extends Endpoint {
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEndPoint that = (PersonEndPoint) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
