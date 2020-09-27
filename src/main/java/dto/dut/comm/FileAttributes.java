package dto.dut.comm;

import dto.MimeTypeData;
import io.netty.handler.codec.http.multipart.FileUpload;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import java.time.LocalDateTime;

/**
 * @author 杨能
 * @create 2020/9/25
 */
public class FileAttributes {
    private static long increaseId = 0;
    protected String fileName;
    protected long fileId;
    protected long fileSize;
    protected MimeType fileType;
    private String path;

    private static long getId() {
        return ++increaseId;
    }


    public FileAttributes(FileUpload fileUpload) throws MimeTypeParseException {
        this.fileSize = fileUpload.length();
        this.fileId = getId();
        this.setFileName(fileUpload.getFilename());
        fileType = new MimeType(fileUpload.getContentType());
    }

    public FileAttributes(String path, String fileName, long fileSize, MimeType fileType) {
        this.fileName = fileName;
        this.fileId = getId();
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileId() {
        return fileId;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
