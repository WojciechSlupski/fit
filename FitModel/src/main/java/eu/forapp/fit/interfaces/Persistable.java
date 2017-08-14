package eu.forapp.fit.interfaces;

import eu.forapp.fit.entity.Value;
import eu.forapp.fit.entity.Values;

import java.time.LocalDate;
import java.util.TreeMap;

public interface Persistable {
    Values read();
    boolean write(Values values);
}
