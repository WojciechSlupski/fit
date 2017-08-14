package eu.forapp.fit.pojo;

import eu.forapp.fit.entity.Value;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AveragePeriodValuesTest {
    private LocalDate beforeDate = LocalDate.of(2015, 12, 15);
    private LocalDate fromDate = LocalDate.of(2016, 1, 1);
    private LocalDate inDate = LocalDate.of(2016, 1, 15);
    private LocalDate toDate = LocalDate.of(2016, 1, 31);
    private LocalDate afterDate = LocalDate.of(2016, 2, 15);

    @Test
    public void constructor_noValue_isNotCalculated(){
        AveragePeriodValues averagePeriodValues = new AveragePeriodValues(new Period(fromDate, toDate));
        assertFalse(averagePeriodValues.isCalculated());
    }

    @Test
    public void constructor_emptyValues_isNotCalculated(){
        AveragePeriodValues averagePeriodValues = new AveragePeriodValues(new ArrayList<>(), new Period(fromDate, toDate));
        assertFalse(averagePeriodValues.isCalculated());
    }

    @Test
    public void constructor_oneDouble_expoectedValues(){
        double value = 10.0;
        ArrayList<Double> list = new ArrayList<>();
        list.add(value);
        AveragePeriodValues result = new AveragePeriodValues(list, new Period(fromDate, toDate));
        assertTrue(result.isCalculated());
        assertEquals(value, result.getAverageValue());
        assertEquals(value, result.getMaxValue());
        assertEquals(value, result.getMinValue());
        assertEquals(value, result.getMedianValue());
        assertEquals(fromDate, result.getPeriod().getFromDate());
        assertEquals(toDate, result.getPeriod().getToDate());
    }

    @Test
    public void constructor_twoDouble_expoectedValues(){
        double valueA = 10.0;
        double valueB = 12.0;
        ArrayList<Double> list = new ArrayList<>();
        list.add(valueB);
        list.add(valueA);
        AveragePeriodValues result = new AveragePeriodValues(list, new Period(fromDate, toDate));
        assertTrue(result.isCalculated());
        assertEquals(11., result.getAverageValue());
        assertEquals(valueB, result.getMaxValue());
        assertEquals(valueA, result.getMinValue());
        assertEquals(11.0, result.getMedianValue());
        assertEquals(fromDate, result.getPeriod().getFromDate());
        assertEquals(toDate, result.getPeriod().getToDate());
    }

    @Test
    public void constructor_threeDouble_expectedValues(){
        double valueA = 10.0;
        double valueB = 11.0;
        double valueC = 18.0;
        ArrayList<Double> list = new ArrayList<>();
        list.add(valueB);
        list.add(valueA);
        list.add(valueC);
        AveragePeriodValues result = new AveragePeriodValues(list, new Period(fromDate, toDate));
        assertTrue(result.isCalculated());
        assertEquals(39.0/3, result.getAverageValue());
        assertEquals(valueC, result.getMaxValue());
        assertEquals(valueA, result.getMinValue());
        assertEquals(valueB, result.getMedianValue());
        assertEquals(fromDate, result.getPeriod().getFromDate());
        assertEquals(toDate, result.getPeriod().getToDate());
    }

    @Test
    public void constructor_threeValues_expectedValues(){
        Value valueA = new Value(0, 0, 0, 10.0, 0, 0);
        Value valueB = new Value(0, 0, 0, 11.0, 0, 0);
        Value valueC = new Value(0, 0, 0, 18.0, 0, 0);
        ArrayList<Value> list = new ArrayList<>();
        list.add(valueC);
        list.add(valueA);
        list.add(valueB);
        AveragePeriodValues result = new AveragePeriodValues(new Period(fromDate, toDate), list);
        assertTrue(result.isCalculated());
        assertEquals(39.0/3, result.getAverageValue());
        assertEquals(valueC.getClose(), result.getMaxValue());
        assertEquals(valueA.getClose(), result.getMinValue());
        assertEquals(valueB.getClose(), result.getMedianValue());
        assertEquals(fromDate, result.getPeriod().getFromDate());
        assertEquals(toDate, result.getPeriod().getToDate());
    }
}