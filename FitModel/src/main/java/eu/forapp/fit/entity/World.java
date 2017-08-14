package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.db.PerstStorage;
import eu.forapp.fit.enums.ShareType;
import org.garret.perst.Persistent;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class World extends Persistent {
    private TreeMap<String, Country> countries;
    private TreeMap<String, WorldShare> worldShares;

    public World(){
        super(PerstStorage.getStorage());
        this.countries = new TreeMap<>();
        this.worldShares = new TreeMap<>();
    }

    public void addCountry(Country country) throws ForAppException {
        boolean added = null == countries.putIfAbsent(country.getNick(), country);
        if(added){
            country.setWorld(this);
            modify();
        } else {
            throw new ForAppException("Duplicate country in World.");
        }
    }

    public void addWorldShare(WorldShare worldShare) throws ForAppException {
        boolean added = null == worldShares.putIfAbsent(worldShare.getName(), worldShare);
        if (added) {
            worldShare.setWorld(this);
            modify();
        } else {
            throw new ForAppException("Duplicate worldShare in World.");
        }
    }

    public Country findCountry(String nick){
        return countries.get(nick);
    }

    Collection<Country> getCountries() {
        return countries.values();
    }

    public WorldShare findShare(String name) {
        return worldShares.get(name);
    }

    Collection<WorldShare> getWorldShares() {
        return worldShares.values();
    }

    public Collection<Share> findSharesBy(ShareType shareType){
        HashSet<Share> result = new HashSet<>();
        result.addAll(getWorldShares().stream().filter(s -> s.getShareType() == shareType).collect(Collectors.toSet()));
        for(Country country: getCountries()){
            result.addAll(country.findSharesBy(shareType));
        }
        return result;
    }
}
