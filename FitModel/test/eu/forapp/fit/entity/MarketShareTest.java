package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.util.RandomValues;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class MarketShareTest {
//    private static String JSON_FILE = "z:/persistent.txt";
//    private static FilePersistent filePersistent = new FilePersistent(JSON_FILE);
    private ShareType shareType;

    MarketShareTest() {
        shareType = ShareType.fromCode(RandomValues.randomInt(1, 6));
    }

    @Test
    public void constructor_new_superIsShare(){
        Share share = new MarketShare();
        assertNotNull(share);
        assertNull(share.getShareType());
        assertNull(share.getName());
    }

    @Test
    public void getName_fromConstructor_expectedGetters() throws ForAppException {
        String expectedName = RandomValues.randomString();
        MarketShare result = new MarketShare(expectedName, shareType);
        assertEquals(expectedName, result.getName());
        assertEquals(shareType, result.getShareType());
    }

    static class PairShareValueTest {

    }
}