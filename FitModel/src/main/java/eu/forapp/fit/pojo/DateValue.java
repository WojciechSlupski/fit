package eu.forapp.fit.pojo;

import eu.forapp.fit.entity.Value;

import java.time.LocalDate;

public class DateValue {
    private LocalDate date;
    private Value value;

    public DateValue(LocalDate date, Value value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Value getValue() {
        return value;
    }
}
