package eu.forapp.fit.entity;

import eu.forapp.fit.db.PerstStorage;
import eu.forapp.fit.db.Root;
import eu.forapp.fit.implementation.FilePersistent;
import eu.forapp.fit.pojo.DateValue;
import eu.forapp.fit.pojo.Period;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class PairShareValueTest {
    private FilePersistent filePersistent = new FilePersistent("z:/persistance.txt");

    private LocalDate beforeDate = LocalDate.of(2015, 12, 15);
    private LocalDate fromDate = LocalDate.of(2016, 1, 1);
    private LocalDate inDate = LocalDate.of(2016, 1, 15);
    private LocalDate toDate = LocalDate.of(2016, 1, 31);
    private LocalDate afterDate = LocalDate.of(2016, 2, 15);

//    @Test
//    public void toJson_oneValue_expected(){
//        PairShareValue pairShareValue = new PairShareValue(filePersistent);
//        DateValue dateValue = createOneValue(0);
//
//        Collection<DateValue> dateValues = new LinkedList<>();
//        dateValues.add(dateValue);
//        dateValues.add(createOneValue(1));
//        dateValues.add(createOneValue(2));
//        pairShareValue.add(dateValues);
//
//        PairShareValue outPairShareValue = new PairShareValue(filePersistent);
//        Collection<Value> results =  outPairShareValue.getValues().find(new Period(dateValue.getDate().minusDays(1), dateValue.getDate().plusDays(1)));
//        assertFalse(results.isEmpty());
//        Value value = (Value) results.toArray()[0];
//        assertEquals(dateValue.getValue().getHigh(), value.getHigh());
//    }

    private DateValue createOneValue(int dayShift) {
        double open = 50.0;
        double high = 100.0;
        double low = 1.0;
        double close = 60.0;
        double volume = 1000000;
        double openInt = 2000;
        Value value = new Value(open, high, low, close, volume, openInt);
        LocalDate localDate = LocalDate.now().plusDays(dayShift);
        return new DateValue(localDate, value);
    }

    @Test
    public void getValues_noData_notNull(){
        PairShareValue pairShareValue = new PairShareValue(null);
        Values result = pairShareValue.getValues();
        assertNotNull(result);
    }

    @Test
    public void find_withData_afterReadFromPersistIsEmpty(){
        String key = RandomValues.randomString();
        PairShareValue pairShareValue = new PairShareValue(null);
        Value value = new Value();
        pairShareValue.add(inDate, value);
        Collection<Value> result = pairShareValue.getValues().find(defauldPeriod());
        assertNotNull(result);
        assertFalse(result.isEmpty());

        PerstStorage.openIfClosed();
        Root root = PerstStorage.getRoot();
        root.putObject(key, pairShareValue);
        PerstStorage.close();
        PerstStorage.open();
        pairShareValue = (PairShareValue) root.popObject(key);
        result = pairShareValue.getValues().find(defauldPeriod());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    private Period defauldPeriod(){
        return new Period(fromDate, toDate);
    }
}