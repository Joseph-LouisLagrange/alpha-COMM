package dao;

import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.endpoint.Endpoint;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class PrivateAlphaRepository extends AlphaRepository {

    protected Endpoint host;

    public PrivateAlphaRepository(Endpoint host) {
        this.host = host;
        this.category = Category.PRIVATE;
    }

    //过去的通信消息，消息记录
    Map<Endpoint, List<Alpha>> endpointListMap = new ConcurrentHashMap<>();

    //对未读消息的缓存
    Map<Endpoint, List<Alpha>> unReadMap = new ConcurrentHashMap<>();


    @Override
    public Endpoint getHost() {
        return this.host;
    }

    @Override
    public AlphaRepository clone() {
        return null;
    }

    @Override
    public List<Alpha> getAll() {
        return null;
    }

    @Override
    public List<Alpha> getAlphasByDataType(DataType dataType) {
        return null;
    }

    @Override
    public List<Alpha> getAlphasByDataTypeAndAction(DataType dataType, Action action) {
        return null;
    }

    @Override
    public List<Alpha> getAlphasByTo(Endpoint to) {
        return null;
    }

    @Override
    public List<Alpha> getAlphasByFrom(Endpoint from) {
        return null;
    }


    @Override
    public void addAlpha(Alpha alpha) {

    }

    @Override
    public void removeAlpha(Endpoint from, Endpoint to) {

    }

    @Override
    public void addUnreadAlpha(Alpha alpha) {

    }

    @Override
    public void popAllUnReadAlpha(Alpha alpha) {

    }


}
