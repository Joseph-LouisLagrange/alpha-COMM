package dto.dut;

import dto.dut.DataUnit;

public class FileDataUnit extends DataUnit<byte[]> {
    private byte[] content;
    public FileDataUnit(){
        super.contentType="FILE";
    }
}
