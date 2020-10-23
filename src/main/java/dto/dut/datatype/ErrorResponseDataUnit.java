package dto.dut.datatype;

/**
 * @author 杨能
 * @create 2020/10/2
 */
public class ErrorResponseDataUnit extends ResponseDataUnit {

    String error;

    public ErrorResponseDataUnit(String error){
        this.error=error;
    }

    @Override
    public String getTypeKey() {
        return "ErrorResponseDataUnit";
    }
}
