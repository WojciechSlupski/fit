package eu.forapp.fit.implementation;

import eu.forapp.fit.db.Root;
import eu.forapp.fit.entity.Value;
import eu.forapp.fit.entity.Values;
import eu.forapp.fit.interfaces.Persistable;

import java.time.LocalDate;
import java.util.TreeMap;

public class ObjectPersist implements Persistable{
    private final Root root;
    private final String key;

    public ObjectPersist(Root root, String key){
        this.root = root;
        this.key = key;
    }

    @Override
    public Values read() {
        Values result;
        try{
            result = (Values) root.popObject(key);
        } catch (Exception e){
            result = null;
        }
        return result;
    }

    @Override
    public boolean write(Values values) {
        try{
            root.putObject(key, values);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
