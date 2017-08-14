package eu.forapp.fit.entity;

import eu.forapp.util.CountryCurrency;
import eu.forapp.util.JsonUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CountryCurrencyTest {

    //@Test
    public void readFromCsv() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("z:/country-code-to-currency-code-mapping.csv"));
        scanner.useDelimiter(",");
        Collection<CountryCurrency> result = new LinkedList<>();
        String line;
        if(scanner.hasNextLine()){
            scanner.nextLine(); // skip header
            //System.out.println(line);
        }
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            String[] items = line.split(",");
            CountryCurrency countryCurrency = new CountryCurrency(items[0], items[1], items[2], items[3]);
            result.add(countryCurrency);
        }
        scanner.close();
        System.out.println(JsonUtil.toJson(result));
    }

    @Test
    public void buildList_notEmpty(){
        List<CountryCurrency> result = CountryCurrency.buildList();
        assertFalse(result.isEmpty());
    }

}