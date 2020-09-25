package dto.dut;


import dto.json.TypeKey;

import java.io.Serializable;

public abstract class DataUnit implements Serializable, TypeKey {
    protected ContentType contentType;

    public String getTypeKey() {
        return this.getClass().getTypeName();
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
