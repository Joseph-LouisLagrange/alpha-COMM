package dto.dut;

public class HtmlTextDataUnit extends SimpleTextDataUnit {
    public HtmlTextDataUnit(String content) {
        super(content);
        this.contentType = ContentType.TEXT_HTML;
    }
}
