package tool;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class ReflectUtil {
    public static boolean isImplInterface(Class<?> clazz,Class<?> inf){
        for(Class<?> c=clazz;c!=Object.class;c=c.getSuperclass()){
            if(Stream.of(c.getInterfaces()).anyMatch(Predicate.isEqual(inf)))
                return true;
        }
        return false;
    }

    public static boolean isExtendClass(Class<?> clazz , Class<?> superClazz){
        for(Class<?> c=clazz.getSuperclass();c!=Object.class;c=c.getSuperclass()){
            if(superClazz.equals(c))
                return true;
        }
        return false;
    }
}
