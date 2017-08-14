package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.generic.Pair;

public class PairShare extends Pair<Share, Share> {
    public PairShare(Share top, Share bottom) throws ForAppException {
        super(top, bottom);
        if(top == bottom){
            throw new ForAppException("PairShare the same share.");
        }
    }

    public String findUniquePairName(){
        StringBuilder sb = new StringBuilder();
        sb.append(getA().getName());
        sb.append("_");
        sb.append(getB().getName());
        return sb.toString();
    }
}
