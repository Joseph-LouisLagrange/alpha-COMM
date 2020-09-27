package dto.endpoint;

import dto.json.gson.TypeKey;

import java.io.Serializable;

/**
 * 通信的端接口
 */
public abstract class Endpoint implements Serializable, TypeKey, Cloneable {
    public String getTypeKey() {
        return this.getClass().getTypeName();
    }

    @Override
    public Endpoint clone() throws CloneNotSupportedException {
        return (Endpoint) super.clone();
    }
}
