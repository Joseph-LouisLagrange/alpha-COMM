package dto.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 杨能
 * @create 2020/9/25
 * json对抽象类的适配处理
 */
public class DefaultAdapter<T extends TypeKey> implements JsonSerializer<T>, JsonDeserializer<T> {


    private final Gson gson = new Gson();

    protected static Map<String, Type> stringTypeMap = new HashMap<>();

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
        stringTypeMap.put(typeKey, typeOfSrc);
        return jsonObject;
    }
}
