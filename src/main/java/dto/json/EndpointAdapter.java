package dto.json;

import com.google.gson.*;

import dto.endpoint.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨能
 * @create 2020/9/25
 */
@Deprecated
public class EndpointAdapter implements JsonSerializer<Endpoint>, JsonDeserializer<Endpoint> {

    private final Gson gson = new Gson();

    protected static Map<String, Type> stringTypeMap = new HashMap<>();

    public EndpointAdapter() {
    }

    @Override
    public JsonElement serialize(Endpoint src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = gson.toJsonTree(src, typeOfSrc).getAsJsonObject();
        if (jsonObject.has("type")) {
            throw new JsonParseException(src + " 中不能包含type属性 ");
        }
        String typeKey = src.getTypeKey();
        jsonObject.addProperty("type", typeKey);
        stringTypeMap.put(typeKey, typeOfSrc);
        return jsonObject;
    }

    @Override
    public Endpoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String typeKey = jsonObject.get("type").getAsString();
        jsonObject.remove("type");
        Type type = stringTypeMap.get(typeKey);
        if (type == null) {
            throw new JsonParseException(typeKey + " 未关联类型 ");
        }
        return gson.fromJson(jsonObject, type);
    }
}
