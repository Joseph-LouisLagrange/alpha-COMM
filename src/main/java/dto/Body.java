package dto;

import dto.dut.DataUnit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Body {
    List<DataUnit<?>> dataUnitList=new ArrayList<>();
    public Body(Collection<DataUnit<?>> collection){
        dataUnitList.addAll(collection);
    }

    public Body(DataUnit<?>[] dataUnits){
        Collections.addAll(dataUnitList,dataUnits);
    }
    public Body(){

    }

    public void addDataUnit(DataUnit<?> dataUnit){
        this.dataUnitList.add(dataUnit);
    }
}
