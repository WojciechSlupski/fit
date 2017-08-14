package eu.forapp.fit.db;

import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerstStorageTest {

    @Test
    public void getRoor_notNull(){
        PerstStorage.openIfClosed();
        Root root = PerstStorage.getRoot();
        PerstStorage.close();
        assertNotNull(root);
    }

    @Test
    public void putItem_insertRandomKey_popEquals(){
        PerstStorage.openIfClosed();
        String key = RandomValues.randomString();
        Item expected = new Item(RandomValues.randomString());
        Root root = PerstStorage.getRoot();
        root.putObject(key, expected);
        PerstStorage.close();

        PerstStorage.open();
        root = PerstStorage.getRoot();
        Item result = (Item)root.popObject(key);
        PerstStorage.close();
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void popItem_randomKey_null(){
        PerstStorage.openIfClosed();
        String key = RandomValues.randomString();
        Root root = PerstStorage.getRoot();
        Item result = (Item)root.popObject(key);
        PerstStorage.close();
        assertNull(result);
    }

    @Test
    public void deleteItem_inserted_removed(){
        PerstStorage.openIfClosed();
        String key = RandomValues.randomString();
        Item expected = new Item(RandomValues.randomString());
        Root root = PerstStorage.getRoot();
        root.putObject(key, expected);
        root.deleteObject(key);
        PerstStorage.close();

        PerstStorage.open();
        root = PerstStorage.getRoot();
        Item result = (Item)root.popObject(key);
        PerstStorage.close();
        assertNull(result);
    }

    @Test
    public void putItem_transientField_transieldFileldNotRestored(){
        PerstStorage.openIfClosed();
        String key = RandomValues.randomString();
        ItemTransientField expected = new ItemTransientField(RandomValues.randomString(), RandomValues.randomString());
        Root root = PerstStorage.getRoot();
        root.putObject(key, expected);
        PerstStorage.close();

        PerstStorage.open();
        root = PerstStorage.getRoot();
        ItemTransientField result = (ItemTransientField)root.popObject(key);
        PerstStorage.close();
        assertEquals(expected.getStore(), result.getStore());
        assertNull(result.getNotStore());
    }
}