package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Country implements Comparable<Country> {

    private String nick;

    private TreeMap<String, Market> markets;
    private TreeMap<String, CountryShare> countryShares;
    private World world;

    private Country(){
        markets = new TreeMap<>();
        countryShares = new TreeMap<>();
    }

    public Country(String nick) throws ForAppException {
        this();
        if(nick != null && !nick.isEmpty()) {
            this.nick = nick;
        } else {
            throw new ForAppException("Country constructor: nick is null or empty.");
        }
    }

    public void addMarket(Market market) throws ForAppException {
        boolean added = null == markets.putIfAbsent(market.getName(), market);
        if(added) {
            market.setCountry(this);
        } else {
            throw new ForAppException("Duplicate market in Country");
        }
    }

    public void addShare(CountryShare countryShare) throws ForAppException {
        boolean added = null == countryShares.putIfAbsent(countryShare.getName(), countryShare);
        if(added){
            countryShare.setCountry(this);
        } else {
            throw new ForAppException("Duplicate countryShare in Country");
        }
    }

    String getNick() {
        return nick;
    }

    Collection<Market> getMarkets(){
        return markets.values();
    }

    Collection<CountryShare> getCountryShares() {
        return countryShares.values();
    }

    Collection<Share> findSharesBy(ShareType shareType) {
        HashSet<Share> result = new HashSet<>();
        result.addAll(getCountryShares().stream().filter(s -> s.getShareType() == shareType).collect(Collectors.toSet()));
        for(Market market: getMarkets()){
            result.addAll(market.findSharesBy(shareType));
        }
        return result;
    }

    void setWorld(World world) {
        this.world = world;
    }

    World getWorld() {
        return world;
    }

    public Market findMarket(String name) {
        return markets.get(name);
    }

    public CountryShare findShare(String name) {
        return countryShares.get(name);
    }

    @Override
    public int compareTo(Country country) {
        return this.nick.compareTo(country.nick);
    }
}
