package eu.forapp.fit.entity;


public class Value {
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final double openInt;

    Value(){
        this.open = 0.0;
        this.high = 0.0;
        this.low = 0.0;
        this.close = 0.0;
        this.volume = 0.0;
        this.openInt = 0.0;
    }

    public Value(double open, double high, double low, double close, double volume, double openInt) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.openInt = openInt;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVolume() {
        return volume;
    }

    public double getOpenInt() {
        return openInt;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Value)) return false;

        Value value = (Value) o;

        if (Double.compare(value.open, open) != 0) return false;
        if (Double.compare(value.high, high) != 0) return false;
        if (Double.compare(value.low, low) != 0) return false;
        if (Double.compare(value.close, close) != 0) return false;
        if (Double.compare(value.volume, volume) != 0) return false;
        return Double.compare(value.openInt, openInt) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(open);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(high);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(low);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(close);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(openInt);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
