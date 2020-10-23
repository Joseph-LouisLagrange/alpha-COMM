package dto;

import dto.dut.DataUnit;
import dto.endpoint.Endpoint;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Alpha implements Cloneable , Serializable {
    Long id;
    Endpoint from;
    Endpoint to;
    DataType dataType;
    Action action;
    LocalDateTime dateTime;
    BaseProtocol baseProtocol;
    List<DataUnit> body=new ArrayList<>();

    public Alpha(Long id, Endpoint from, Endpoint to, DataType dataType, Action action, BaseProtocol baseProtocol, List<DataUnit> body) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.dataType = dataType;
        this.action = action;
        this.dateTime = LocalDateTime.now();
        this.baseProtocol = baseProtocol;
        this.body = body;
    }

    public Alpha() {
    }

    public Alpha(long id){
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endpoint getFrom() {
        return from;
    }

    public void setFrom(Endpoint from) {
        this.from = from;
    }

    public Endpoint getTo() {
        return to;
    }

    public void setTo(Endpoint to) {
        this.to = to;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<DataUnit> getBody() {
        return body;
    }

    public void setBody(List<DataUnit> body) {
        this.body = body;
    }

    public BaseProtocol getBaseProtocol() {
        return baseProtocol;
    }

    public void setBaseProtocol(BaseProtocol baseProtocol) {
        this.baseProtocol = baseProtocol;
    }

    public void addDataUnit(DataUnit dataUnit){
        this.body.add(dataUnit);
    }

    @Override
    public String toString() {
        return "Alpha{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", dataType=" + dataType +
                ", action=" + action +
                ", date=" + dateTime +
                ", baseProtocol=" + baseProtocol +
                ", body=" + body +
                '}';
    }

    @Override
    public Alpha clone() {
        Alpha alpha=new Alpha();
        alpha.setBody(new ArrayList<>(this.getBody()));
        alpha.setDateTime(this.dateTime);
        alpha.setFrom(this.from);
        alpha.setAction(this.action);
        alpha.setDataType(this.dataType);
        alpha.setId(this.id);
        alpha.setTo(this.to);
        alpha.setBaseProtocol(this.baseProtocol);
        return alpha;
    }

    public Alpha alphaMetaClone(){
        Alpha alpha=this.clone();
        alpha.getBody().clear();
        return alpha;
    }
}
