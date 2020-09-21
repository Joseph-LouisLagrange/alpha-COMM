package dto.dut;

import dto.dut.DataUnit;

import java.io.InputStream;

public class FileDataUnit extends DataUnit<InputStream> {
    private String fileName;
    protected transient InputStream content;

    public FileDataUnit() {
        super.contentType = "FILE";
    }
}
