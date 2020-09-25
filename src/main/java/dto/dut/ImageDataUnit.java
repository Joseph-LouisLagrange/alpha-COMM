package dto.dut;

import dto.dut.DataUnit;

public class ImageDataUnit extends DataUnit {
    private String base64;

    public ImageDataUnit(String base64, ContentType contentType) {
        this.base64 = base64;
        this.contentType = contentType;
        this.contentType = ContentType.IMAGE_JPEG;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
