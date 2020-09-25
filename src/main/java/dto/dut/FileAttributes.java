package dto.dut;

import javax.activation.MimeType;
import java.time.LocalDateTime;

/**
 * @author 杨能
 * @create 2020/9/25
 */
public class FileAttributes {
    protected String fileName;
    protected String fileId;
    protected long fileSize;
    protected MimeType fileType;


    public FileAttributes(String fileName, String fileId, long fileSize, MimeType fileType) {
        this.fileName = fileName;
        this.fileId = fileId;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public MimeType getFileType() {
        return fileType;
    }

    public void setFileType(MimeType fileType) {
        this.fileType = fileType;
    }
}
