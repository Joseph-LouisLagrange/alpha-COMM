package dto.dut;

import java.io.InputStream;

public class FileDataUnit extends DataUnit {

    private transient InputStream inputStream = null;

    FileAttributes fileAttributes;

    public FileDataUnit(FileAttributes fileAttributes, ContentType contentType, InputStream inputStream) {
        this.contentType = contentType;
        this.fileAttributes = fileAttributes;
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
