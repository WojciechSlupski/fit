package eu.forapp.fit.pojo;

import java.time.LocalDate;

public class Period {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Period(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
