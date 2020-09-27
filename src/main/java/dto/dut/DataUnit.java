package dto.dut;


import dto.json.gson.TypeKey;

import java.io.Serializable;

public abstract class DataUnit implements Serializable, TypeKey {

    public DataUnit() {
    }

    ;

    /**
     * 建议子类重写为:
     * public String getTypeKey() {
     * return this.getClass().getSimpleName();
     * }
     *
     * @return 当前类型的标识键
     */
    public String getTypeKey() {
        return this.getClass().getTypeName();
    }
}
