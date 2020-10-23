package dao.repository;

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

    private static long eid = 0;

    /**
     * 库的性质
     */
    public enum Category {
        PRIVATE, PUBLIC
    }

    protected Category category = null;

    /**
     * 库的唯一id
     */
    private long id;

    public AlphaRepository(){
        setId(nextId());
    }

    protected long nextId(){
        return ++eid;
    }

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
