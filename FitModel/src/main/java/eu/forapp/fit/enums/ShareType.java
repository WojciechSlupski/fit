package eu.forapp.fit.enums;

public enum ShareType {

    Unknown(0),
    Bonds(1),
    Commodities(2),
    Currencies(3),
    Indicies(4),
    Etfs(5),
    Stocks(6);

    private final int value;

    ShareType(int code) {
        this.value = code;
    }

    public final int getValue() {
        return this.value;
    }

    public static ShareType fromCode(int code) {
        for (ShareType at : values()) {
            if (at.getValue() == code) {
                return at;
            }
        }
        return null;
    }
}
