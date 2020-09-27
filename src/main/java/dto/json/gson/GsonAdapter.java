package dto.json.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public interface GsonAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {
}
