package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;

public class MarketShare extends Share {
    private Market market;

    public MarketShare(){
        super();
    }

    public MarketShare(String name, ShareType shareType) throws ForAppException {
        super(name, shareType);
    }

    public Market getMarket(){
        return market;
    }

    void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("[");
        sb.append(getName());
        sb.append(":");
        sb.append(market.getName());
        sb.append(":");
        sb.append(market.getCountry().getNick());
        sb.append(", ");
        sb.append(getShareType().toString());
        sb.append("]");
        return sb.toString();
    }

}
