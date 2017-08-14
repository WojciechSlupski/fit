package eu.forapp.fit.db;

import org.garret.perst.Storage;
import org.garret.perst.StorageFactory;

public class PerstStorage {
    private static Storage db;

    static {
        db = StorageFactory.getInstance().createStorage();
        open();
    }

    public static Root getRoot(){
        Root root = db.getRoot();
        if(root == null){
            root = new Root(db);
            db.setRoot(root);
        }
        return root;
    }

    public static void open(){
        //db.open("z://fit.dbs", Storage.DEFAULT_PAGE_POOL_SIZE / 4);
        db.open("c://box//fit.dbs", Storage.DEFAULT_PAGE_POOL_SIZE / 4);
    }

    public static void openIfClosed(){
        if(!db.isOpened()) {
            open();
        }
    }


    public static void close(){
        db.close();
    }

    public static Storage getStorage() {
        return db;
    }
}
