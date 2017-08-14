package eu.forapp.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RandomValues {
    public static Date randomBeginYear(int from, int to){
        int randomYear = randomInt(from, to);
        int mounth = 1;
        int day = 1;
        Date date = ValueCsvReaderTest.DateUtil.dateFrom(randomYear, mounth, day);
        return date;
    }
	
	public static boolean randomBoolean(){
		int item = randomInt() % 2;		// z zakresu 2 wartości
		return item != 0;
	}
	
	public static String randomString() {
		return UUID.randomUUID().toString();
	}

	public static String randomString6() {
		return randomString().substring(0, 5);
	}

	public static String randomString2() {
        StringBuilder sb = new StringBuilder();
        sb.append(randomChar());
        sb.append(randomChar());
        return sb.toString();
	}
	
	public static int randomInt() {
		Random random = new Random();
		return random.nextInt(1000000);
	}
	
	public static int randomInt(int from, int to){
		Random random = new Random();
		int rc = random.nextInt(to - from + 1);
		return rc + from;
	}

	public static float randomFloat() {
		Random random = new Random();
		return random.nextFloat() * 10000 * random.nextFloat();
	}

	public static Double randomDouble() {
		Random random = new Random();
		return random.nextDouble() * 1.07 * random.nextDouble();
	}
    
//    public static BigDecimal randomBigDecimal(){
//        return new BigDecimal(randomDouble());
//    }

    public static BigDecimal randomBigDecimal(){
        return new BigDecimal(randomInt(1, 1000000));
    }

    public static BigDecimal randomBigDecimal(int from, int to){
        return new BigDecimal(randomInt(from, to));
    }
    
    public static char randomChar() {
        int randomChar = randomInt(30, 122);
        //Character ch = new Character(randomChar);
        char rc = (char) randomChar;
        return rc;
    }

    public static Date randomDate() {
        String format = String.format("%04d-%02d-%02d", randomInt(1950, 2050), randomInt(1, 12), randomInt(1, 28));
        return java.sql.Date.valueOf(format);
    }

    public static Date randomDateBeforeToday() {
        Date today = ValueCsvReaderTest.DateUtil.today();
        int yearToday = ValueCsvReaderTest.DateUtil.year(today);
        int monthToday = ValueCsvReaderTest.DateUtil.month(today);
        int dayToday = ValueCsvReaderTest.DateUtil.day(today);
        String format = String.format("%04d-%02d-%02d", randomInt(1950, yearToday), randomInt(1, monthToday), randomInt(1, dayToday));
        return java.sql.Date.valueOf(format);
    }

    public static Date randomDateAfterToday() {
        Date today = ValueCsvReaderTest.DateUtil.today();
        int yearToday = ValueCsvReaderTest.DateUtil.year(today);
        int monthToday = ValueCsvReaderTest.DateUtil.month(today);
        int randomYear = randomInt(yearToday, 2050);
        int randomMonth = randomInt(monthToday, 12);
        int dayToday = ValueCsvReaderTest.DateUtil.day(today);
        int maxDay = ValueCsvReaderTest.DateUtil.lastDay(yearToday, monthToday);
        int randomDay;
        if(dayToday == maxDay){
            randomDay = maxDay;
        } else {
            randomDay = randomInt(dayToday, maxDay);
        }
        String format = String.format("%04d-%02d-%02d", randomYear, randomMonth, randomDay);
        return java.sql.Date.valueOf(format);
    }
}
