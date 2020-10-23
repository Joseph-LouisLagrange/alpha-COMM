package dao;

import dto.dut.safe.BasicAuthenticateDataUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 杨能
 * @create 2020/10/1
 */
public class InMemoryUserRepository implements UserRepository{
    private static final UserRepository userRepository = new InMemoryUserRepository();

    private static List<BasicAuthenticateDataUnit> authentication=new ArrayList<>();

    static {
        //测试数据
        authentication.add(new BasicAuthenticateDataUnit("admin1","admin1"));
        authentication.add(new BasicAuthenticateDataUnit("admin2","admin2"));
        authentication.add(new BasicAuthenticateDataUnit("admin3","admin3"));
    }
    private InMemoryUserRepository(){

    }

    public static UserRepository getInstance(){
        return userRepository;
    }
    @Override
    public boolean login(BasicAuthenticateDataUnit basicAuthenticateDataUnit) {
        return authentication.stream().anyMatch(Predicate.isEqual(basicAuthenticateDataUnit));
    }

    @Override
    public boolean register(BasicAuthenticateDataUnit basicAuthenticateDataUnit) {
        if(authentication.stream().anyMatch(b->b.getUserName().equals(basicAuthenticateDataUnit.getUserName()))){
            return false;
        }
        authentication.forEach(System.out::println);
        return authentication.add(basicAuthenticateDataUnit);
    }
}
