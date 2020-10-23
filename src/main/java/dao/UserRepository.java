package dao;

import dto.dut.safe.BasicAuthenticateDataUnit;

/**
 * @author 杨能
 * @create 2020/10/1
 */
public interface UserRepository {
    public boolean login(BasicAuthenticateDataUnit basicAuthenticateDataUnit);

    public boolean register(BasicAuthenticateDataUnit basicAuthenticateDataUnit);
}
