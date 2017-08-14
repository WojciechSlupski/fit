package eu.forapp.fit.service;

import org.junit.jupiter.api.Test;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class YahooServiceTest {
    @Test
    public void get_existingStockSymbol_returned() throws IOException {
        Stock stock = YahooFinance.get("goog", true);
        stock.print();
        for(HistoricalQuote quote: stock.getHistory()){
            System.out.println(quote.toString());
        }
        assertNotNull(stock);
    }

    @Test
    public void get_existingEtfSymbol_returned() throws IOException {
        Stock stock = YahooFinance.get("gdxj");
        stock.print();
        assertNotNull(stock);
    }

    @Test
    public void get_existingIndicesSymbol_returned() throws IOException {
        Stock stock = YahooFinance.get("^dax");
        stock.print();
        assertNotNull(stock);
    }

    @Test
    public void get_existingSymbol_returned() throws IOException {
        Stock stock = YahooFinance.get("AA01.BE");
        stock.print();
        assertNotNull(stock);
    }
}
