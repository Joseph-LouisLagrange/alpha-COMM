package dto.dut.datatype;


import dto.dut.DataUnit;

/**
 * @author 杨能
 * @create 2020/10/1
 * 几乎没用
 */
public class ResponseDataUnit extends DataUnit {

    String message;

    public ResponseDataUnit(String message){
        this.message=message;
    }

    public ResponseDataUnit(){}

    @Override
    public String getTypeKey() {
        return "ResponseDataUnit";
    }
}
