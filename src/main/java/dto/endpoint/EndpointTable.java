package dto.endpoint;

import java.lang.reflect.Type;

/**
 * @author 杨能
 * @create 2020/9/25
 */
@Deprecated
public interface EndpointTable {
    public Type getType(String key);

    public String getKey(Type type);

    public Type put(String key, Type type);

    public Type remove(String key);

    default String getTypeString(String key) {
        return getType(key).getTypeName();
    }
}
