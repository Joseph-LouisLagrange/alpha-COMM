package dto.dut.comm;

import dto.dut.DataUnit;

public class PhizDataUnit extends DataUnit {

    private Character emailCode = null;

    public PhizDataUnit(Character emailCode) {
        this.emailCode = emailCode;
    }

    public Character getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(Character emailCode) {
        this.emailCode = emailCode;
    }
}
