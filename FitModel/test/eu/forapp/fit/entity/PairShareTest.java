package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairShareTest {

    @Test
    public void constructor_sameParams_exception() throws ForAppException {
        Share share = new Share(RandomValues.randomString(), ShareType.Bonds);
        try{
            new PairShare(share, share);
            fail("Should be exception");
        } catch (ForAppException e) {
            // OK
        } catch(Exception e){
            fail("Should be ForAppException.");
        }
    }

    @Test
    public void constructor_differentParams_nothing() throws ForAppException {
        Share shareTop = new Share(RandomValues.randomString(), ShareType.Bonds);
        Share shareBottom = new Share(RandomValues.randomString(), ShareType.Bonds);
        PairShare result = new PairShare(shareTop, shareBottom);
        assertNotNull(result);
    }
}