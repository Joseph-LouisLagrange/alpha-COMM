package core.net;

import service.Service;
import dto.json.AlphaJsonConverter;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public interface Registrar {
    public void registerService(Service service);

    public void registerAlphaJsonConverter(AlphaJsonConverter alphaJsonConverter);

}
