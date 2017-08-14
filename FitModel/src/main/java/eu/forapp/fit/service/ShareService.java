package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.db.PerstStorage;
import eu.forapp.fit.db.Root;
import eu.forapp.fit.entity.*;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.fit.pojo.Context;
import eu.forapp.fit.pojo.DateValue;
import eu.forapp.util.CountryCurrency;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class ShareService {
    private static World world;
    private static ShareData shareData;

    static {
        Root root = PerstStorage.getRoot();
        world = root.popWorld();
        if(world == null) {
            world = new World();
            root.putWord(world);
        }
        shareData = root.popShareData();
        if(shareData == null){
            shareData = new ShareData();
            root.putShareData(shareData);
        }
    }

    static Collection<Share> findSharesBy(ShareType shareType){
        return world.findSharesBy(shareType);
    }

    static TreeMap<Share, Values> findValues(Collection<Share> shares) throws ForAppException {
        return shareData.findValues(shares);
    }

    static void pompCountryAndCurrency() throws ForAppException {
        List<CountryCurrency> list = CountryCurrency.buildList();
        for(CountryCurrency countryCurrency: list){
            Country country = world.findCountry(countryCurrency.getCountryCod());
            if(country == null) {
                country = new Country(countryCurrency.getCountryCod());
                world.addCountry(country);
            }
        }
    }

//    public void addValue(Context topContext, Context bottomContext, LocalDate date, Value value) throws ForAppException {
//        PairShareValue pairShareValue = findPairShareValue(topContext, bottomContext);
//        pairShareValue.add(date, value);
//    }

    static void addValue(Context topContext, Context bottomContext, Collection<DateValue> dateValues) throws ForAppException {
        //System.out.println("  " + Integer.toString(dateValues.size())); // TODO usunąć, to tylko do testów
        PairShareValue pairShareValue = findPairShareValue(topContext, bottomContext);
        pairShareValue.add(dateValues);
    }

    static private PairShareValue findPairShareValue(Context topContext, Context bottomContext) throws ForAppException {
        Share topShare = findOrCreateShare(topContext);
        Share bottomShare = findOrCreateShare(bottomContext);

        // TODO usunąć ten fragment, bo jest tymczasowo do wyświetlania
//        String sb = topShare.toString() + " == " + bottomShare.toString();
//        System.out.println(sb);

        PairShare pairShare = new PairShare(topShare, bottomShare);
        return shareData.findOrCreatePairValue(pairShare);
    }

    static private Share findOrCreateShare(Context context) throws ForAppException {
        Share share;
        if(context.isCountry()) {
            Country country = findOrAddCountry(context);
            if (context.isMarket()) {
                Market market = findOrAddMarket(country, context);
                share = findOrAddShare(market, context);
            } else {
                share = findOrAddShare(country, context);
            }
        } else {
            share = findOrAddShare(context);
        }
        return share;
    }

    static private Share findOrAddShare(Context context) throws ForAppException {
        WorldShare worldShare = world.findShare(context.share);
        if(worldShare == null){
            worldShare = new WorldShare(context.share, context.shareType);
            world.addWorldShare(worldShare);
        }
        return worldShare;
    }

    static private Share findOrAddShare(Market market, Context context) throws ForAppException {
        MarketShare marketShare = market.findShare(context.share);
        if(marketShare == null){
            marketShare = new MarketShare(context.share, context.shareType);
            market.addMarketShare(marketShare);
        }
        return marketShare;
    }

    static private Share findOrAddShare(Country country, Context context) throws ForAppException {
        CountryShare countryShare = country.findShare(context.share);
        if(countryShare == null){
            countryShare = new CountryShare(context.share, context.shareType);
            country.addShare(countryShare);
        }
        return countryShare;
    }

    static private Country findOrAddCountry(Context context) throws ForAppException {
        Country country = world.findCountry(context.country);
        if(country == null) {
            country = new Country(context.country);
            world.addCountry(country);
        }
        return country;
    }

    static private Market findOrAddMarket(Country country, Context context) throws ForAppException {
        Market market = country.findMarket(context.market);
        if(market == null){
            market = new Market(context.market);
            country.addMarket(market);
        }
        return market;
    }
}
