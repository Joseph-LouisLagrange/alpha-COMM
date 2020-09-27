package dto.dut.comm;

import core.net.netty.http.SimpleHttpOutHandler;
import dto.dut.DataUnit;

public class SimpleTextDataUnit extends DataUnit {

    private String content = null;

    public SimpleTextDataUnit() {

    }

    public SimpleTextDataUnit(String content) {
        this.content = content;
    }

    @Override
    public String getTypeKey() {
        return "SimpleTextDataUnit";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
