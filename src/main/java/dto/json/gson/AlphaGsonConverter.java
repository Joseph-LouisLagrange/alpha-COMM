package dto.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Alpha;
import dto.json.AlphaJsonConverter;

import java.lang.reflect.Type;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class AlphaGsonConverter implements AlphaJsonConverter {
    protected GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping();

    private Gson getGson() {
        return gsonBuilder.create();
    }

    @Override
    public String toJson(Alpha alpha) {
        return getGson().toJson(alpha);
    }

    @Override
    public Alpha fromJson(String jsonString) {
        return getGson().fromJson(jsonString, Alpha.class);
    }

    public void registerTypeHierarchyAdapter(Class<?> type, GsonAdapter<?> gsonAdapter) {
        gsonBuilder.registerTypeHierarchyAdapter(type, gsonAdapter);
    }

    public <T> void supportAbsJson(Class<T> clazz, GsonAdapter<T> gsonAdapter) {
        gsonBuilder.registerTypeHierarchyAdapter(clazz, gsonAdapter);
    }

}
