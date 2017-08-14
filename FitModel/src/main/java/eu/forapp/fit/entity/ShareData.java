package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.db.PerstStorage;
import eu.forapp.fit.db.Root;
import eu.forapp.fit.implementation.FilePersistent;
import eu.forapp.fit.implementation.ObjectPersist;
import eu.forapp.fit.interfaces.Persistable;
import org.garret.perst.Persistent;
import org.garret.perst.Storage;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ShareData extends Persistent {
    private static String JSON_FOLDER = "c:/box/fitdata";       // TODO informacje o katalogu powinny być przekazane z zewnątrz, np. przy wywołaniu funkcjo
    private TreeMap<PairShare, PairShareValue> pairs;

    public ShareData() {
        super(PerstStorage.getStorage());
        pairs = new TreeMap<>();
    }

    public PairShareValue findOrCreatePairValue(PairShare pairShare) throws ForAppException {
        PairShareValue pairShareValue = findPairShareValue(pairShare);
        if(pairShareValue == null){
            tryJoinPair(pairShare);
            pairShareValue = new PairShareValue(givePersistentable(JSON_FOLDER, pairShare.findUniquePairName()));
            pairs.put(pairShare, pairShareValue);
            modify();
        }
        return pairShareValue;
    }

    public TreeMap<Share, Values> findValues(Collection<Share> shares) throws ForAppException {
        TreeMap<Share, Values> result = new TreeMap<>();
        for(Share share: shares){
            Values values = findValues(share);
            if(values != null){
                result.put(share, values);
            }
        }
        return result;
    }

    private Values findValues(Share top) throws ForAppException {
        Collection<Share> shares = top.getShares();
        if(!shares.isEmpty()){
            Share bottom = (Share) shares.toArray()[0];
            PairShareValue pairShareValue = findPairShareValue(top, bottom);
            if(pairShareValue != null){
                return pairShareValue.getValues();
            }
        }
        return null;
    }

    private PairShareValue findPairShareValue(Share top, Share bottom) throws ForAppException {
        PairShare pairShare = new PairShare(top, bottom);
        PairShareValue pairShareValue = findPairShareValue(pairShare);
        if(pairShareValue == null){
            pairShare = new PairShare(bottom, top);
            pairShareValue = findPairShareValue(pairShare);
        }
        return pairShareValue;
    }

    private PairShareValue findPairShareValue(PairShare pairShare){
        return pairs.get(pairShare);
    }

    private void tryJoinPair(PairShare pairShare) throws ForAppException {
        Share left = pairShare.getA();
        Share right = pairShare.getB();
        boolean isJoined = left.containJoin(right) || right.containJoin(left);
        if(!isJoined){
            left.join(right);
        }
    }

    private Persistable givePersistentable(String folder, String name){
//        boolean byObject = true;
//        if(byObject){
//            return new ObjectPersist(PerstStorage.getRoot(), name);
//        } else {
//            return new FilePersistent(folder + "/" + name + ".txt");
//        }
        return new ObjectPersist(PerstStorage.getRoot(), name);
    }
}
