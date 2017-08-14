package eu.forapp.fit.service;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;
import eu.forapp.fit.pojo.Effect;
import eu.forapp.fit.pojo.Period;
import eu.forapp.fit.pojo.Range;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ShareValueServiceTest {

    @Test
    void findDeclineBefore() throws ForAppException {
        ShareValueService shareValueService = new ShareValueService();
        Range decline = new Range(0.80, 0.90);
        LocalDate fromDate = LocalDate.of(2017, 1, 1);
        LocalDate toDate = LocalDate.of(2017, 5, 31);
        Period secondPeriod = new Period(fromDate, toDate);
        Collection<Effect> result = shareValueService.findDeclineBefore(decline, secondPeriod, ShareType.Etfs);
        printEffects(result);
        //assertFalse(result.isEmpty());
    }

    private void printEffects(Collection<Effect> effects) {
        int i = 0;
        for(Effect effect: effects){
            StringBuilder sb = new StringBuilder();
            sb.append(++i);
            sb.append("  ");
            sb.append(effect.getShare().toString());
            System.out.println(sb.toString());
        }
        if(i == 0) {
            System.out.println("Effect is empty");
        }
    }
}