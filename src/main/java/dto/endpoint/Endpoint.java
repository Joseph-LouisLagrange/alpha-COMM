package dto.endpoint;

import dto.json.TypeKey;

import java.io.Serializable;

/**
 * 通信的端接口
 */
public abstract class Endpoint implements Serializable, TypeKey {
    public String getTypeKey() {
        return this.getClass().getTypeName();
    }
}
