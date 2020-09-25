package dto.json;

import com.google.gson.*;
import dto.dut.DataUnit;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 杨能
 * @create 2020/9/25
 */
@Deprecated
public class DataUnitAdapter implements JsonSerializer<DataUnit>, JsonDeserializer<DataUnit> {
    private final Gson gson = new Gson();

    protected static Map<String, Type> stringTypeMap = new HashMap<>();

    @Override
    public DataUnit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return null;
    }

    @Override
    public JsonElement serialize(DataUnit src, Type typeOfSrc, JsonSerializationContext context) {

        return null;
    }
}
