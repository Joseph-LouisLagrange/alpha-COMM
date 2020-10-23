package dao;

import dto.Alpha;
import dto.endpoint.Endpoint;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/10/1
 */
public interface CacheStorage {
    public Endpoint getHost();
    public void push(Alpha alpha);
    public Alpha pop();
    public List<Alpha> popAll();
    public Alpha get();
    public boolean isEmpty();
    public List<Alpha> getAll();
}
