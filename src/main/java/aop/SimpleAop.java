package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class SimpleAop<T> implements InvocationHandler {
    T object=null;
    public SimpleAop(T object){
        this.object=object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
