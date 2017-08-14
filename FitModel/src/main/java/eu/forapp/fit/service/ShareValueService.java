package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.entity.Share;
import eu.forapp.fit.entity.Values;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.fit.pojo.Effect;
import eu.forapp.fit.pojo.Period;
import eu.forapp.fit.pojo.Range;
import eu.forapp.fit.pojo.Twin;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;

class ShareValueService {
    Collection<Effect> findDeclineBefore(Range decline, Period secondPeriod, ShareType shareType) throws ForAppException {
        HashSet<Effect> result = new HashSet<>();
        System.out.println("Start");
        Collection<Share> shares = ShareService.findSharesBy(shareType);
        System.out.println("Shares found, " + Integer.toString(shares.size()));
        TreeMap<Share, Values> shareValuesMap = ShareService.findValues(shares);
        System.out.println("SharesValuesMap found, " + Integer.toString(shareValuesMap.size()));
        if(!shareValuesMap.isEmpty()) {
            for (Share share = shareValuesMap.firstKey(); share != null; share = shareValuesMap.higherKey(share)) {
                Values values = shareValuesMap.get(share);
                Twin twin = values.findDeclineBefore(decline, secondPeriod);
                if (twin.isCalculated()) {
                    result.add(new Effect(share, twin));
                }
            }
        }
        return result;
    }
}
