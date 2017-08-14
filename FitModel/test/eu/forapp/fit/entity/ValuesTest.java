package eu.forapp.fit.entity;

import eu.forapp.fit.pojo.Period;
import eu.forapp.fit.pojo.Range;
import eu.forapp.fit.pojo.Twin;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ValuesTest {
    private LocalDate beforeDate = LocalDate.of(2014, 12, 15);
    private LocalDate fromDate = LocalDate.of(2016, 1, 1);
    private LocalDate inDate = LocalDate.of(2016, 1, 15);
    private LocalDate toDate = LocalDate.of(2016, 1, 31);
    private LocalDate afterDate = LocalDate.of(2016, 2, 15);

    @Test
    void find_noData_empty(){
        Values values = new Values();
        Collection<Value> result = values.find(defauldPeriod());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void find_addedIn_expectedResult(){
        Values values = new Values();
        Value value = new Value();
        values.add(inDate, value);
        Collection<Value> result = values.find(defauldPeriod());
        assertFalse(result.isEmpty());
        assertTrue(result.contains(value));
    }

    @Test
    void find_addedBefore_expectedResult(){
        outOfPeriodTest(beforeDate);
    }

    @Test
    void find_addedAfter_expectedResult(){
        outOfPeriodTest(afterDate);
    }

    @Test
    void add_sameDateAndValue_replacedValue(){
        Values values = new Values();
        Value value = new Value(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Value secondValue = new Value(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        values.add(inDate, value);
        values.add(inDate, secondValue);
        Collection<Value> result = values.find(defauldPeriod());

        assertFalse(result.isEmpty());
        assertSame(result.toArray()[0], value);
        assertNotSame(result.toArray()[0], secondValue);
    }

    @Test
    void add_sameDateAndDifferentValue_replacedValue(){
        Values values = new Values();
        Value value = new Value(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Value secondValue = new Value(2.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        values.add(inDate, value);
        values.add(inDate, secondValue);
        Collection<Value> result = values.find(defauldPeriod());

        assertFalse(result.isEmpty());
        assertSame(result.toArray()[0], secondValue);
        assertNotSame(result.toArray()[0], value);
    }

    @Test void findDeclineBefore_testValues80_isCalculatedTrue(){
        Values values = new Values(declineTestValues());
        Twin result = values.findDeclineBefore(new Range(0.80, 0.99), defauldPeriod());
        assertTrue(result.isCalculated());
    }

    @Test void findDeclineBefore_testValues60_90_isCalculatedFalse(){
        Values values = new Values(declineTestValues());
        Twin result = values.findDeclineBefore(new Range(0.60, 0.86), defauldPeriod());
        assertFalse(result.isCalculated());
    }

    @Test
    void findDeclineBefore_testValules89_isCalculatedTrue(){
        Values values = new Values(declineTestValues());
        Twin result = values.findDeclineBefore(new Range(0.89, 0.99), defauldPeriod());
        assertTrue(result.isCalculated());
    }

    @Test void findDeclineBefore_testValules91_isCalculatedFalse(){
        Values values = new Values(declineTestValues());
        Twin result = values.findDeclineBefore(new Range(0.91, 0.99), defauldPeriod());
        assertFalse(result.isCalculated());
    }

    private TreeMap<LocalDate,Value> declineTestValues() {
        TreeMap<LocalDate,Value> values = new TreeMap<>();
        values.put(beforeDate.minusMonths(1), new Value(0, 0, 0, 90, 0, 0));
        values.put(beforeDate, new Value(0, 0, 0, 100, 0, 0));
        values.put(beforeDate.plusMonths(1), new Value(0, 0, 0, 80, 0, 0));
        values.put(beforeDate.plusMonths(2), new Value(0, 0, 0, 70, 0, 0));
        values.put(beforeDate.plusMonths(3), new Value(0, 0, 0, 60, 0, 0));
        values.put(beforeDate.plusMonths(4), new Value(0, 0, 0, 50, 0, 0));
        values.put(beforeDate.plusMonths(5), new Value(0, 0, 0, 40, 0, 0));
        values.put(beforeDate.plusMonths(6), new Value(0, 0, 0, 30, 0, 0));

        values.put(fromDate, new Value(0, 0, 0, 20, 0, 0));
        values.put(inDate, new Value(0, 0, 0, 10, 0, 0));
        values.put(toDate, new Value(0, 0, 0, 20, 0, 0));
        values.put(afterDate, new Value(0, 0, 0, 5, 0, 0));
        return values;
    }

    private void outOfPeriodTest(LocalDate date){
        Values values = new Values();
        Value value = new Value();
        values.add(date, value);
        Collection<Value> result = values.find(defauldPeriod());
        assertTrue(result.isEmpty());
    }

    private Period defauldPeriod(){
        return new Period(fromDate, toDate);
    }
}