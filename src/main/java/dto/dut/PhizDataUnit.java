package dto.dut;

import dto.dut.DataUnit;

public class PhizDataUnit extends DataUnit<Character> {
    public static final String PHIZ_TYPE="PHIZ";
    public PhizDataUnit(char email){
        this.contentType=PHIZ_TYPE;
        this.content=email;
    }

    public PhizDataUnit(int code){
        this.content=(char)code;
    }
}
