package dto.dut;

import dto.dut.DataUnit;

/**
 * @author 杨能
 * @create 2020/9/27
 */
public class AdviceDataUnit extends DataUnit {
    protected String adviceMessage = null;

    public AdviceDataUnit() {
    }

    public AdviceDataUnit(String adviceMessage) {
        this.adviceMessage = adviceMessage;
    }

    @Override
    public String getTypeKey() {
        return "AdviceDataUnit";
    }
}
