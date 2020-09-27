package dto;

import dto.dut.DataUnit;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Body implements Cloneable {
    List<DataUnit> dataUnitList = new ArrayList<>();

    public Body(Collection<DataUnit> collection) {
        dataUnitList.addAll(collection);
    }

    public Body(DataUnit[] dataUnits) {
        Collections.addAll(dataUnitList, dataUnits);
    }

    public Body() {

    }

    public void addDataUnit(DataUnit dataUnit) {
        this.dataUnitList.add(dataUnit);
    }

    public List<DataUnit> getDataUnitList() {
        return dataUnitList;
    }

    @Override
    public Body clone() throws CloneNotSupportedException {
        return (Body) super.clone();
    }
}
