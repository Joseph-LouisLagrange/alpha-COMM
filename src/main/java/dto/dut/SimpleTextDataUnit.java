package dto.dut;

import dto.dut.DataUnit;

public class SimpleTextDataUnit extends DataUnit {

    public SimpleTextDataUnit(String content) {
        this.contentType = ContentType.TEXT_PLAIN;
    }

    @Override
    public String getTypeKey() {
        return this.getClass().getSimpleName();
    }
}
