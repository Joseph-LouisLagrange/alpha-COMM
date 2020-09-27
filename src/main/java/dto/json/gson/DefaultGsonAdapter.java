package dto.json.gson;

import com.google.gson.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 杨能
 * @create 2020/9/25
 * json对抽象类的适配处理
 */
public class DefaultGsonAdapter<T extends TypeKey> implements GsonAdapter<T> {

    private final Gson gson = new Gson();

    protected static Map<String, Type> stringTypeMap = new HashMap<>();

    public DefaultGsonAdapter(Class<T> tClass) {
        long count = Arrays.stream(tClass.getDeclaredConstructors())
                .mapToInt(Constructor::getParameterCount)
                .filter(value -> value == 0)
                .count();
        if (count == 0) {
            //表示没有提供无参构造
            throw new JsonParseException(tClass + " 未提供无参构造方法 ");
        }
        int mod = tClass.getModifiers();
        if (!Modifier.isAbstract(mod) && !Modifier.isInterface(mod)) {
            try {
                stringTypeMap.put(tClass.newInstance().getTypeKey(), tClass);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeKey = jsonObject.get("type").getAsString();
        jsonObject.remove("type");
        Type type = stringTypeMap.get(typeKey);
        if (type == null) {
            throw new JsonParseException(typeKey + " 未关联类型 ");
        }
        return gson.fromJson(jsonObject, type);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = gson.toJsonTree(src, typeOfSrc).getAsJsonObject();
        if (jsonObject.has("type")) {
            throw new JsonParseException(src + " 中不能包含type属性 ");
        }
        String typeKey = src.getTypeKey();
        jsonObject.addProperty("type", typeKey);
        return jsonObject;
    }
}
