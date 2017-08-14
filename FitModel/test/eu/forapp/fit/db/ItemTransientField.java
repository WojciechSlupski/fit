package eu.forapp.fit.db;

public class ItemTransientField {
    private String store;
    private transient String notStore;

    public ItemTransientField(String store, String notStore) {
        this.store = store;
        this.notStore = notStore;
    }

    public String getStore() {
        return store;
    }

    public String getNotStore() {
        return notStore;
    }
}
