package dto.dut.comm;

import dto.dut.DataUnit;

import javax.activation.MimeType;

public class ImageDataUnit extends DataUnit {
    private String base64;

    private MimeType mimeType;

    public ImageDataUnit(String base64, MimeType mimeType) {
        this.base64 = base64;
        this.mimeType = mimeType;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }
}
