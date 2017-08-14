package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    public void newCountry_word_passedWord() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        assertNull(country.getWorld());
    }

    @Test
    public void newCountry_markets_notNullEmptyList() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        assertNotNull(country.getMarkets());
        assertTrue(country.getMarkets().isEmpty());
    }

    @Test
    public void getName_fromConstructor_expected() throws ForAppException {
        String expected = RandomValues.randomString();
        Country result = new Country(expected);
        assertEquals(expected, result.getNick());
    }

    @Test
    public void addMarket_newMarket_marketHasCountry() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        Market market = new Market(RandomValues.randomString());
        country.addMarket(market);
        assertEquals(country, market.getCountry());
    }

    @Test
    public void addShare_newCountryShare_shareHasCountry() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        CountryShare countryShare = new CountryShare(RandomValues.randomString(), ShareType.Bonds);
        country.addShare(countryShare);
        assertEquals(country, countryShare.getCountry());
    }

    @Test
    public void addMarket_manyMarkets_orderedCollection() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        for(int i = 10; i >= 0; i--) {
            Market market = new Market(RandomValues.randomString());
            country.addMarket(market);
        }
        String last = "";
        for(Market market: country.getMarkets() ){
            assertTrue(last.compareTo(market.getName()) <= 0);
            last = market.getName();
        }
    }

    @Test
    public void addMarket_duplicateMarket_exception() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        Market market = new Market(RandomValues.randomString());
        Market secondMarket = new Market(market.getName());
        country.addMarket(market);
        try{
            country.addMarket(secondMarket);
            fail("Should be exception");
        } catch (ForAppException e){
            // OK
        } catch (Exception e){
            fail("Should be ForAppException");
        }
        assertEquals(1, country.getMarkets().size());
        assertTrue(country.getMarkets().contains(market));
        assertNull(secondMarket.getCountry());
    }

    @Test
    public void addCountryShare_duplicateShare_exception() throws ForAppException {
        Country country = new Country(RandomValues.randomString());
        CountryShare countryShare = new CountryShare(RandomValues.randomString(), ShareType.Bonds);
        CountryShare secondCountryShare = new CountryShare(countryShare.getName(), ShareType.Bonds);
        country.addShare(countryShare);
        try{
            country.addShare(secondCountryShare);
            fail("Should be exception");
        } catch (ForAppException e){
            // OK
        } catch (Exception e){
            fail("Should be ForAppException");
        }
        assertEquals(1, country.getCountryShares().size());
        assertTrue(country.getCountryShares().contains(countryShare));
        assertNull(secondCountryShare.getCountry());
    }
}