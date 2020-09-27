package dao;

import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.endpoint.Endpoint;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class PublicAlphaRepository extends AlphaRepository {
    @Override
    public Endpoint getHost() {
        return null;
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
