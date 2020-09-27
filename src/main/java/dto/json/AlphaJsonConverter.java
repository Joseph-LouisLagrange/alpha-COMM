package dto.json;

import dto.Alpha;

/**
 * @author 杨能
 * @create 2020/9/27
 * Json的转换器接口，负责实现JsonString与alpha包之间的互转
 */
public interface AlphaJsonConverter {
    public String toJson(Alpha alpha);

    public Alpha fromJson(String jsonString);
}
