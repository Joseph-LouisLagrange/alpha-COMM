package dto.dut.safe;

import dto.dut.DataUnit;

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
}
