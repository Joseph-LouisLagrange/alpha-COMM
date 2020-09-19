package dto.dut;

import dto.dut.DataUnit;

public class StringDataUnit extends DataUnit<String> {
    public static final String STRING_TYPE="STRING";
    public StringDataUnit(){
        super.contentType=STRING_TYPE;
    }
}
