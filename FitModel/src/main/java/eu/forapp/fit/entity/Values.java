package eu.forapp.fit.entity;

import eu.forapp.fit.pojo.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeMap;

public class Values {
    private TreeMap<LocalDate, Value> treeMapValues;

    Values() {
        treeMapValues = new TreeMap<>();
    }

    public Values(TreeMap<LocalDate, Value> treeMapValues){
        this.treeMapValues = treeMapValues;
    }

    Collection<Value> find(Period period){
        return treeMapValues.subMap(period.getFromDate(), period.getToDate()).values();
    }

    public Twin findDeclineBefore(Range decline, Period secondPeriod){
        AveragePeriodValues first;
        AveragePeriodValues second = findAveragePeriod(secondPeriod);
        if(second.isCalculated()){
            Range range = new Range(second.getMinValue() / (1.0 - decline.getFrom()), second.getMinValue() / (1.0 - decline.getTo()) );
            first = findFirstAveragePeriodBefore(second.getPeriod().getFromDate(), range);
        } else {
            first = new AveragePeriodValues();
        }
        return new Twin(first, second);
    }

    private AveragePeriodValues findAveragePeriod(Period period){
        Collection<Value> values = find(period);
        return new AveragePeriodValues(period, values);
    }

    private AveragePeriodValues findFirstAveragePeriodBefore(LocalDate beforeDate, Range range) {
        AveragePeriodValues result = null;
        LocalDate current = treeMapValues.lowerKey(beforeDate);
        while(current != null){
            Value tempValue = treeMapValues.get(current);
            if(tempValue.getClose() >= range.getFrom() && result == null){
                result = new AveragePeriodValues(current, tempValue);   // pierwsza data spełniająca warunek
            }
            if(tempValue.getClose() > range.getTo()){
                return new AveragePeriodValues();                       // przekroczenie zakresu, brak wyniku
            }
            current = treeMapValues.lowerKey(current);
        }
        return result != null ? result : new AveragePeriodValues();
    }

    boolean add(Collection<DateValue> dateValues){
        boolean result = false;
        for(DateValue dateValue: dateValues){
            if(add(dateValue.getDate(), dateValue.getValue())){
                result = true;
            }
        }
        return result;
    }

    boolean add(LocalDate date, Value value) {
        Value currentValue = treeMapValues.get(date);
        boolean shouldAdd = !value.equals(currentValue);
        if(shouldAdd) {
            treeMapValues.put(date, value);
        }
        return shouldAdd;
    }
}
