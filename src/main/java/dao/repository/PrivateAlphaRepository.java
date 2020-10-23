package dao.repository;

import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.endpoint.Endpoint;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class PrivateAlphaRepository extends AlphaRepository {

    //与库相关联的Endpoint
    Set<Endpoint> endpoints=new HashSet<>(2);

    //不可见库(删除的消息但是不是   撤回   ),只是单向的不可视
    Map<Endpoint,Set<Alpha>> invisibleRepository=new HashMap<>(2);

    //过去的通信消息，消息记录
    Map<Endpoint, List<Alpha>> messageStorage = new HashMap<>(2);

    public PrivateAlphaRepository(Endpoint endpoint1,Endpoint endpoint2) {
        this.category = Category.PRIVATE;
        endpoints.add(endpoint1);
        endpoints.add(endpoint2);

    }

    @Override
    public AlphaRepository clone() {
        return null;
    }

    @Override
    public List<Alpha> getAll() {
        return messageStorage.values().stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Alpha::getDateTime))
                .collect(Collectors.toList());
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
        Endpoint endpoint=alpha.getFrom();
        this.messageStorage.get(endpoint).add(alpha);
    }

    @Override
    public void removeAlpha(Endpoint from, Endpoint to) {

    }
}
