package dto;

import java.time.LocalDate;

public class Alpha {
    long id;
    Endpoint from;
    Endpoint to;
    DataType dataType;
    Action action;
    LocalDate date;
    Body body;

    public Alpha(long id, Endpoint from, Endpoint to, DataType dataType, Action action, Body body) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.dataType = dataType;
        this.action = action;
        this.date = LocalDate.now();
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
