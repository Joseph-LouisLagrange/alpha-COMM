package dto.endpoint;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author 杨能
 * @create 2020/9/25
 */
@Deprecated
public class DefaultEndpointTable implements EndpointTable {

    Map<String, Type> stringTypeMap = new HashMap<>();

    Map<Type, String> typeStringMap = new HashMap<>();

    @Override
    public Type getType(String key) {
        return stringTypeMap.get(key);
    }

    @Override
    public String getKey(Type type) {
        return typeStringMap.get(type);
    }

    @Override
    public Type put(String key, Type type) {
        typeStringMap.put(type, key);
        return stringTypeMap.put(key, type);
    }

    @Override
    public Type remove(String key) {
        Type type = stringTypeMap.remove(key);
        if (type != null) {
            typeStringMap.remove(type);
        }
        return type;
    }
}
