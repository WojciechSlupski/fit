package eu.forapp.fit.pojo;

import eu.forapp.fit.entity.Value;
import javafx.collections.transformation.SortedList;

import java.time.LocalDate;
import java.util.*;

import static java.util.Arrays.asList;

public class AveragePeriodValues {
    private Period period;
    private double minValue;
    private double maxValue;
    private double averageValue;
    private double medianValue;
    private boolean calculated;

    public AveragePeriodValues() {
        minValue = 0;
        maxValue = 0;
        averageValue = 0;
        medianValue = 0;
        calculated = false;
    }

    AveragePeriodValues(Period period) {
        this();
        this.period = period;
    }

    public AveragePeriodValues(Period period, Collection<Value> values) {
        this(period);
        List<Double> sortedList = convertFromValuesToSorted(values);
        calculate(sortedList);
    }

    public AveragePeriodValues(LocalDate date, Value value){
        this(new Period(date, date), Collections.singletonList(value));
    }

    AveragePeriodValues(Collection<Double> values, Period period) {
        this(period);
        List<Double> sortedList = convertToSorted(values);
        calculate(sortedList);
    }

    private void calculate(List<Double> sortedList) {
        calculated = sortedList.size() > 0;
        if(calculated) {
            calculateMinMax(sortedList);
            calculateMedian(sortedList);
        }
    }

    public Period getPeriod() {
        return period;
    }

    public double getMinValue() {
        return minValue;
    }

    double getMaxValue() {
        return maxValue;
    }

    double getAverageValue() {
        return averageValue;
    }

    double getMedianValue() {
        return medianValue;
    }

    public boolean isCalculated() {
        return calculated;
    }

    private void calculateMinMax(List<Double> values){
        Double currentMin = Double.MAX_VALUE;
        Double currentMax = Double.MIN_VALUE;
        Double sum = 0.0;
        for (Double value : values) {
            if(value < currentMin) currentMin = value;
            if(value > currentMax) currentMax = value;
            sum += value;
        }
        minValue = currentMin;
        maxValue = currentMax;
        averageValue = sum / values.size();
    }

    private void calculateMedian(List<Double> values) {
        int size = values.size();
        if(size > 1) {
            if (size % 2 == 1) {
                medianValue = values.get(size / 2);
            } else {
                medianValue = (values.get((size / 2) - 1) + values.get(size / 2)) / 2.0;
            }
        } else {
            medianValue = averageValue;
        }
    }

    private List<Double> convertFromValuesToSorted(Collection<Value> values){
        ArrayList<Double> list = new ArrayList<>(values.size());
        for(Value value: values){
            list.add(value.getClose());
        }
        Collections.sort(list);
        return list;
    }

    private List<Double> convertToSorted(Collection<Double> values) {
        ArrayList<Double> list = new ArrayList<>(values);
        Collections.sort(list);
        return list;
    }
}
