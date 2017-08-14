package eu.forapp.fit.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryShareTest {

    @Test
    public void constructor_new_superIsShare(){
        Share share = new CountryShare();
        assertNotNull(share);
        assertNull(share.getShareType());
        assertNull(share.getName());
    }
}