package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;

public class WorldShare extends Share {
    private World world;

    public WorldShare(String name, ShareType shareType) throws ForAppException {
        super(name, shareType);
    }

    public World getWorld(){
        return world;
    }

    void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("[");
        sb.append(getName());
        sb.append(", ");
        sb.append(getShareType().toString());
        sb.append("]");
        return sb.toString();
    }

}
