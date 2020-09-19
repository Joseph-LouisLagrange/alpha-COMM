package dao;

import dto.Action;
import dto.Alpha;
import dto.DataType;
import dto.Endpoint;

import java.io.Serializable;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public abstract class AlphaRepository implements Serializable , Cloneable{
    public enum Category{
        PRIVATE,PUBLIC
    };

    protected Category category=Category.PUBLIC;

    private long id;

    public abstract AlphaRepository clone();

    public abstract List<Alpha> getAll();

    public abstract List<Alpha> getAlphasByDataType(DataType dataType);

    public abstract List<Alpha> getAlphasByDataTypeAndAction(DataType dataType , Action action);

    public abstract List<Alpha> getAlphasByTo(Endpoint to);

    public abstract List<Alpha> getAlphasByFrom(Endpoint from);

    public Category getCategory(){
        return this.category;
    }

    public abstract void setPrivate(Endpoint user);

    public abstract void addAlpha(Alpha alpha);

    public abstract void removeAlpha(Endpoint from , Endpoint to);

    public long getId(){
        return id;
    }

    protected void setId(long id){
        this.id=id;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+" ["+getCategory()+" :: "+getId()+"]";
    }
}
