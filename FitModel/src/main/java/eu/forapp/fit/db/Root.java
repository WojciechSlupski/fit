package eu.forapp.fit.db;

import eu.forapp.fit.entity.ShareData;
import eu.forapp.fit.entity.World;
import org.garret.perst.Index;
import org.garret.perst.Persistent;
import org.garret.perst.Storage;

public class Root extends Persistent {
    private static String WORLD_KEY = "world_key";
    private  static String SHARE_DATA_KEY = "share_data_key";
//    private  static String VALUE_SERVICE_KEY = "value_service_key";
    private final Index<Object> objects;

    Root(Storage storage){
        super(storage);
        objects = storage.createIndex(String.class, true);
    }

//    public void putValueService(ShareService valueService){
//        objects.set(VALUE_SERVICE_KEY, valueService);
//    }
//
//    public ShareService popValueService(){
//        return (ShareService) objects.get(VALUE_SERVICE_KEY);
//    }

    public void putWord(World world){
        objects.set(WORLD_KEY, world);
    }

    public World popWorld(){
        return (World) objects.get(WORLD_KEY);
    }

    public void putShareData(ShareData shareData){
        objects.set(SHARE_DATA_KEY, shareData);
    }

    public ShareData popShareData() {
        return (ShareData) objects.get(SHARE_DATA_KEY);
    }

    public void putObject(String key, Object item){
        objects.set(key, item);
    }

    public Object popObject(String key){
        return objects.get(key);
    }

    void deleteObject(String key){
        objects.remove(key);
    }
}
