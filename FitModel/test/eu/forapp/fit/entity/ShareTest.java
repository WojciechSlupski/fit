package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShareTest {
    private ShareType shareType;

    ShareTest() {
        shareType = ShareType.fromCode(RandomValues.randomInt(1, 6));
    }

    @Test
    public void defaultConstructor_new_expectedGetters(){
        Share result = new Share();
        assertNull(result.getShareType());
        assertNull(result.getName());

    }

    @Test
    public void getName_fromConstructor_expectedGetters() throws ForAppException {
        String expectedName = RandomValues.randomString();
        Share result = new Share(expectedName, shareType);
        assertEquals(expectedName, result.getName());
        assertEquals(shareType, result.getShareType());
    }

    @Test
    public void getShares_new_empty() throws ForAppException {
        Share share = new Share(RandomValues.randomString(), shareType);
        assertTrue(share.getShares().isEmpty());
    }

    @Test
    public void join_secondShare_expectedRelation() throws ForAppException {
        Share shareA = new Share(RandomValues.randomString(), shareType);
        Share shareB = new Share(RandomValues.randomString(), shareType);

        shareA.join(shareB);

        assertTrue(shareA.getShares().contains(shareB));
        assertTrue(shareB.getShares().contains(shareA));
    }

    @Test
    public void join_this_exception() throws ForAppException {
        Share share = new Share(RandomValues.randomString(), shareType);
        try{
            share.join(share);
            fail("Should be exception");
        } catch (ForAppException e){
            // OK
        } catch(Exception e){
            fail("Should be ForAppException");
        }
    }

    @Test
    public void join_duplicate_exception() throws ForAppException {
        Share share = new Share(RandomValues.randomString(), shareType);
        Share secondShare = new Share(RandomValues.randomString(), shareType);
        share.join(secondShare);
        try{
            share.join(secondShare);
            fail("Should be exception");
        } catch (ForAppException e){
            // OK
        } catch(Exception e){
            fail("Should be ForAppException");
        }
    }
}