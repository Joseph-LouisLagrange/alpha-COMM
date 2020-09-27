package dao;

import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.endpoint.Endpoint;

import java.io.Serializable;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public abstract class AlphaRepository implements Serializable, Cloneable {
    /**
     * 库的性质
     */
    public enum Category {
        PRIVATE, PUBLIC
    }

    ;

    protected Category category = Category.PUBLIC;

    private static long increaseId = 0;

    public long nextId() {
        return increaseId++;
    }

    /**
     * 库的唯一id
     */
    private long id;

    public abstract Endpoint getHost();

    public abstract AlphaRepository clone();

    public abstract List<Alpha> getAll();

    public abstract List<Alpha> getAlphasByDataType(DataType dataType);

    public abstract List<Alpha> getAlphasByDataTypeAndAction(DataType dataType, Action action);

    public abstract List<Alpha> getAlphasByTo(Endpoint to);

    public abstract List<Alpha> getAlphasByFrom(Endpoint from);

    public Category getCategory() {
        return this.category;
    }

    public abstract void addAlpha(Alpha alpha);

    public abstract void removeAlpha(Endpoint from, Endpoint to);

    public abstract void addUnreadAlpha(Alpha alpha);

    public abstract void popAllUnReadAlpha(Alpha alpha);

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+" ["+getCategory()+" :: "+getId()+"]";
    }
}
