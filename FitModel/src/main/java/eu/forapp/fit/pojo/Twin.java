package eu.forapp.fit.pojo;

public class Twin {
    private final AveragePeriodValues first;
    private final AveragePeriodValues second;

    public Twin(AveragePeriodValues first, AveragePeriodValues second) {
        this.first = first;
        this.second = second;
    }

    public boolean isCalculated(){
        return first.isCalculated() && second.isCalculated();
    }
}
