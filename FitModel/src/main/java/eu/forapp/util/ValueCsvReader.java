package eu.forapp.util;

import eu.forapp.fit.entity.Value;
import eu.forapp.fit.pojo.DateValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class ValueCsvReader {
    Scanner scanner;

    ValueCsvReader(){
    }

    public ValueCsvReader(Path path) throws FileNotFoundException {
        scanner = new Scanner(path.toFile());
        scanner.useDelimiter(",");
    }

    public ValueCsvReader(String fileName) throws FileNotFoundException {
        scanner = new Scanner(new File(fileName));
        scanner.useDelimiter(",");
    }

    public Collection<DateValue> read(){
        Collection<DateValue> result = new LinkedList<>();
        if(scanner.hasNextLine()){
            scanner.nextLine(); // skip header
        }
        while(scanner.hasNextLine()){
            result.add(readLine(scanner.nextLine()));
        }        
        return result;
    }

    DateValue readLine(String next) {
        String[] array = next.split(",");

        String dateString = array[0];
        int y = Integer.valueOf(dateString.substring(0, 4));
        int m = Integer.valueOf(dateString.substring(4, 6));
        int d = Integer.valueOf(dateString.substring(6, 8));
        LocalDate date = LocalDate.of(y, m, d);

        double open = Double.parseDouble(array[1]);
        double high = Double.parseDouble(array[2]);
        double low = Double.parseDouble(array[3]);
        double close = Double.parseDouble(array[4]);
        double volume = Double.parseDouble(array[5]);
        double openInt = Double.parseDouble(array[6]);
        Value value = new Value(open, high, low, close, volume, openInt);

        return new DateValue(date, value);
    }

    public void close(){
        scanner.close();
    }

}
