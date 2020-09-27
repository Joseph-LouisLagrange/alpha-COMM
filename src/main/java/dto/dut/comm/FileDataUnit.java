package dto.dut.comm;

import dto.dut.DataUnit;
import dto.dut.comm.FileAttributes;

public class FileDataUnit extends DataUnit {

    //防止json
    private transient byte[] content = null;

    FileAttributes fileAttributes;

    public FileDataUnit() {
    }

    public FileDataUnit(FileAttributes fileAttributes, byte[] content) {
        this.fileAttributes = fileAttributes;
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public FileAttributes getFileAttributes() {
        return fileAttributes;
    }

    @Override
    public String getTypeKey() {
        return "FileDataUnit";
    }
}
