package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.entity.Share;
import eu.forapp.fit.entity.Values;
import eu.forapp.fit.enums.ShareType;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShareServiceTest {

    @Test
    void findSharesBy_type_notEmpty(){
        ShareService shareService = new ShareService();
        Collection<Share> result = shareService.findSharesBy(ShareType.Etfs);
        assertFalse(result.isEmpty());
    }

    @Test
    void findValues_type_notEmpty() throws ForAppException {
        ShareService shareService = new ShareService();
        Collection<Share> shares = shareService.findSharesBy(ShareType.Etfs);
        Map<Share, Values> result =  shareService.findValues(shares);
        assertFalse(result.isEmpty());
    }
}