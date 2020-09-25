package dto.dut;

import dto.dut.DataUnit;

public class PhizDataUnit extends DataUnit {

    private Character emailCode = null;

    public PhizDataUnit(Character emailCode) {
        this.emailCode = emailCode;
        this.contentType = ContentType.PHIZ;
    }

    public Character getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(Character emailCode) {
        this.emailCode = emailCode;
    }
}
