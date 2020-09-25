package dto.fast;

import dto.Body;
import dto.dut.DataUnit;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class BodyBuilder {
    private Body body = null;
    public BodyBuilder(){
        this.body=new Body();
    }

    public BodyBuilder add(DataUnit dataUnit) {
        this.body.addDataUnit(dataUnit);
        return this;
    }

    public Body build(){
        return this.body;
    }
}
