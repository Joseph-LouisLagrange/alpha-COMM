package dto.dut.datatype;

/**
 * @author 杨能
 * @create 2020/10/2
 */
public class FailResponseDataUnit extends ResponseDataUnit {

    private String reason=null;

    public FailResponseDataUnit(){}

    public FailResponseDataUnit(String reason){
        this.reason=reason;
    }
    @Override
    public String getTypeKey() {
        return "FailResponseDataUnit";
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
