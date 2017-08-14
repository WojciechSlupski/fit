package eu.forapp.util;

import eu.forapp.fit.entity.Value;
import eu.forapp.fit.pojo.DateValue;
import eu.forapp.util.ValueCsvReader;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ValueCsvReaderTest {

    @Test
    public void readLine_sample_expectedValues(){
        String line = "20080818,45.273,45.373,44.573,44.673,28051,13";
        ValueCsvReader valueCsvReader = new ValueCsvReader();
        DateValue result = valueCsvReader.readLine(line);
        Value value = result.getValue();
        assertEquals(LocalDate.of(2008, 8, 18), result.getDate());
        assertEquals( Double.parseDouble("45.273"), value.getOpen());
        assertEquals( Double.parseDouble("45.373"), value.getHigh());
        assertEquals( Double.parseDouble("44.573"), value.getLow());
        assertEquals( Double.parseDouble("44.673"), value.getClose());
        assertEquals( Double.parseDouble("28051"), value.getVolume());
        assertEquals( Double.parseDouble("13"), value.getOpenInt());
    }

    /**
     *
     * @author Wojtek
     */
    public static class DateUtil {
        static int denominator = 1000;


        public static Date dateFrom(int year, int mounth, int day) {
            String format = String.format("%04d-%02d-%02d", year, mounth, day);
            return Date.valueOf(format);
        }

        public static Timestamp timestampFrom(int year, int mounth, int day) {
            Date date = dateFrom(year, mounth, day);
            return new Timestamp(date.getTime());
        }

        public static Date addDays(java.util.Date date, int days) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, days);
            return new Date(c.getTimeInMillis());
        }

        public static Timestamp fromDate(Date date) {
            return new Timestamp(date.getTime());
        }

        public static Date minDate() {
            return dateFrom(1900, 1, 1);
        }

        public static Date maxDate() {
            return dateFrom(2999, 12, 31);
        }

        public static boolean equals(Date left, Date right) {
            return left.getTime() / denominator == right.getTime() / denominator;
        }

        public static boolean equalsTimestampDate(Timestamp left, Date right) {
            return left.getTime() / denominator == right.getTime() / denominator;
        }

        public static String getCurrentAsString() {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
            java.util.Date today = GregorianCalendar.getInstance().getTime();
            return df.format(today);
        }

        public static String toString(java.util.Date date){
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
             return simpleDateFormat.format(date);
        }

        public static Date today(){
            java.util.Date date =  GregorianCalendar.getInstance().getTime();
            return new Date(date.getTime());
        }

        public static int day(java.util.Date date){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public static int month(java.util.Date date){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.MONTH) + 1;
        }

        public static int year(java.util.Date date){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        }

        public static boolean isBetween(java.util.Date date, java.util.Date start, java.util.Date end){
            boolean isStart = date.compareTo(start) >= 0;
            boolean isEnd = date.compareTo(end)<= 0;
            return isStart && isEnd;
        }

        public static int lastDay(int year, int month){
            java.util.Date date = dateFrom(year, month, 1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        public static boolean isLastDayInMonth(java.util.Date date) {
            int day = lastDay(year(date), month(date));
            return day == day(date);
        }
    }
}