package dto.dut.safe;

import dto.dut.DataUnit;

import java.util.Objects;

/**
 * @author 杨能
 * @create 2020/9/26
 */
public class BasicAuthenticateDataUnit extends DataUnit {
    private String userName;
    private String password;

    public BasicAuthenticateDataUnit(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public BasicAuthenticateDataUnit(){}

    @Override
    public String getTypeKey() {
        return "AuthenticateDataUnit";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAuthenticateDataUnit that = (BasicAuthenticateDataUnit) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    @Override
    public String toString() {
        return "BasicAuthenticateDataUnit{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
