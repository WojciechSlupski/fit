package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Market implements Comparable<Market> {
    private String name;
    private Country country;
    private TreeMap<String, MarketShare> marketShares;

    public Market(String name) throws ForAppException {
        if(name != null && !name.isEmpty()) {
            this.marketShares = new TreeMap<>();
            this.name = name;
        } else {
            throw new ForAppException("Market constructor: name is null or empty.");
        }
    }

    public void addMarketShare(MarketShare marketShare) throws ForAppException {
        boolean added = null == marketShares.putIfAbsent(marketShare.getName(), marketShare);
        if (added) {
            marketShare.setMarket(this);
        } else {
            throw new ForAppException("Duplicate marketShare in Market.");
        }
    }

    Collection<Share> findSharesBy(ShareType shareType) {
        return getMarketShares().stream().filter(s -> s.getShareType() == shareType).collect(Collectors.toSet());
    }

    Collection<MarketShare> getMarketShares() {
        return this.marketShares.values();
    }

    void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public MarketShare findShare(String name) {
        return marketShares.get(name);
    }

//    @Override
//    public boolean equals(Object other) {
//        if(other == null) return false;
//        if(this == other) return true;
//        if (other.getClass() != this.getClass()) {
//            return false;
//        }
//        Market market = (Market) other;
//        return this.name.equals(market.name);
//    }

    @Override
    public int compareTo(Market market) {
        return this.name.compareTo(market.name);
    }
}
