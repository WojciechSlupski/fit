package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;

import java.util.Collection;
import java.util.HashSet;

public class Share implements Comparable<Share> {
    private String name;
    private ShareType shareType;
    private HashSet<Share> shares;

    public Share() {
        shares = new HashSet<>();
    }

    public Share(String name, ShareType shareType) throws ForAppException {
        this();
        if(name != null && !name.isEmpty() && shareType != null) {
            this.name = name;
            this.shareType = shareType;
        } else {
            throw new ForAppException("Share constructor: name is null or empty or shareType is null.");
        }
    }

    public void join(Share share) throws ForAppException {
        if(this == share) throw new ForAppException("Cannot join with myself.");
        boolean added = this.shares.add(share);
        if(added) {
            share.shares.add(this);
        } else {
            throw new ForAppException("Duplicate share in join.");
        }
    }

    public String getName() {
        return name;
    }

    public ShareType getShareType() {
        return shareType;
    }

    public Collection<Share> getShares() {
        return shares;
    }

    public boolean containJoin(Share share){
        return shares.contains(share);
    }

    @Override
    public int compareTo(Share share) {
        return this.name.compareTo(share.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append(name);
        sb.append(", ");
        sb.append(shareType.toString());
        sb.append("]");
        return sb.toString();
    }
}
