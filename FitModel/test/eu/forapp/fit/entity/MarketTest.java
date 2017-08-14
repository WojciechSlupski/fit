package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketTest {
    private ShareType shareType;

    MarketTest() {
        shareType = ShareType.fromCode(RandomValues.randomInt(1, 6));
    }

    @Test
    public void getName_fromConstructor_expectedName() throws ForAppException {
        String expected = RandomValues.randomString();
        Market result = new Market(expected);
        assertEquals(expected, result.getName());
    }

    @Test
    public void addShare_newShare_shareHasMarket() throws ForAppException {
        Market market = new Market(RandomValues.randomString());
        MarketShare marketShare = new MarketShare(RandomValues.randomString(), ShareType.Bonds);
        market.addMarketShare(marketShare);
        assertEquals(market, marketShare.getMarket());
    }

    @Test
    public void addShare_manyShares_orderedCollection() throws ForAppException {
        Market market = new Market(RandomValues.randomString());
        for(int i = 10; i >= 0; i--) {
            MarketShare marketShare = new MarketShare(RandomValues.randomString(), ShareType.Bonds);
            market.addMarketShare(marketShare);
        }
        String last = "";
        for(MarketShare marketShare: market.getMarketShares() ){
            assertTrue(last.compareTo(marketShare.getName()) <= 0);
            last = marketShare.getName();
        }
    }

    @Test
    public void addCountry_duplicateName_exception() throws ForAppException {
        Market market = new Market(RandomValues.randomString());
        MarketShare marketShare = new MarketShare(RandomValues.randomString(), shareType);
        MarketShare secontMarketShare = new MarketShare(marketShare.getName(), shareType);
        market.addMarketShare(marketShare);
        try {
            market.addMarketShare(secontMarketShare);
            fail("Should be exception");
        } catch(ForAppException e){
            // OK
        } catch (Exception e){
            fail("Should be ForAppException.");
        }
        assertEquals(1, market.getMarketShares().size());
        assertTrue(market.getMarketShares().contains(marketShare));
        assertNull(secontMarketShare.getMarket());
    }
}