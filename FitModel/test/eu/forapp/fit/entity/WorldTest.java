package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void getCountries_construction_empty(){
        World world = new World();
        assertTrue(world.getCountries().isEmpty());
    }

    @Test
    public void addCountry_manyCountries_nameSortedCollection() throws ForAppException {
        World world = new World();
        for(int i = 10; i >= 0; i--) {
            Country country = new Country(RandomValues.randomString());
            world.addCountry(country);
        }
        String last = "";
        for(Country country: world.getCountries() ){
            assertTrue(last.compareTo(country.getNick()) <= 0);
            last = country.getNick();
        }
    }

    @Test
    public void addCountry_duplicateName_exception() throws ForAppException {
        World world = new World();
        Country country = new Country(RandomValues.randomString());
        world.addCountry(country);
        Country second = new Country(country.getNick());
        try {
            world.addCountry(second);
            fail("Should be exception");
        } catch(ForAppException e){
            // OK
        } catch (Exception e){
            fail("Should be ForAppException.");
        }
        assertEquals(1, world.getCountries().size());
        assertTrue(world.getCountries().contains(country));
        assertNull(second.getWorld());
    }

    @Test
    public void addShare_newShare_shareHasMarket() throws ForAppException {
        World world = new World();
        WorldShare worldShare = new WorldShare(RandomValues.randomString(), ShareType.Bonds);
        world.addWorldShare(worldShare);
        assertEquals(world, worldShare.getWorld());
    }

    @Test
    public void addShare_manyShares_orderedCollection() throws ForAppException {
        World world = new World();
        for(int i = 10; i >= 0; i--) {
            WorldShare worldShare = new WorldShare(RandomValues.randomString(), ShareType.Bonds);
            world.addWorldShare(worldShare);
        }
        String last = "";
        for(WorldShare worldShare: world.getWorldShares() ){
            assertTrue(last.compareTo(worldShare.getName()) <= 0);
            last = worldShare.getName();
        }
    }

//    @Test
//    public void ccc() throws ForAppException {
//        World word = new World();
//        Country country = new Country(RandomValues.randomString());
//        word.addCountry(country);
//        Country find = new Country(country.getNick());
//        Collection<Country> countries = word.getCountries();
//        assertTrue(countries.contains(find));
//
//    }
}