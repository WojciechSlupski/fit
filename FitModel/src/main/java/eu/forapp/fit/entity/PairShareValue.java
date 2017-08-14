package eu.forapp.fit.entity;

import eu.forapp.fit.interfaces.Persistable;
import eu.forapp.fit.pojo.DateValue;

import java.time.LocalDate;
import java.util.Collection;

public class PairShareValue {
    private transient Values values;
    private Persistable persistable;
    private boolean valuesAreDirty = false;

    PairShareValue(Persistable persistable) {
        this.persistable = persistable;
    }

    public Values getValues(){
        beforeFind();
        Values result = values;
        writeIfDirty();
        return result;
    }

    public void add(Collection<DateValue> dateValues){
        beforeChangeValue();
        if(values.add(dateValues)) {
            write();
        }
    }

    void add(LocalDate date, Value value) {
        beforeChangeValue();
        if(values.add(date, value)) {
            dirty();
        }
    }

    private void beforeChangeValue() {
        if(!isValue()){
            read();
            createNewValueIfNot();
        }
    }

    private void beforeFind() {
        if(!isValue()){
            read();
            createNewValueIfNot();
        }
    }

    private void createNewValueIfNot() {
        if(!isValue()){
            values = new Values();
            clearDirty();
        }
    }

    private boolean isValue(){
        return values != null;
    }

    private void read(){
        if(persistable != null){
            this.values = persistable.read();
            clearDirty();
        }
    }

    private void writeIfDirty(){
        if(isDirty()){
            write();
        } else {
            values = null;
        }
    }

    private void write() {
        if (persistable != null) {
            boolean ok = persistable.write(this.values);
            if (ok) {
                values = null;
                clearDirty();
            }
        }
    }

    private void dirty() {
        valuesAreDirty = true;
    }

    private void clearDirty() {
        valuesAreDirty = false;
    }

    private boolean isDirty(){
        return valuesAreDirty;
    }
}
