package eu.forapp.fit.pojo;

import eu.forapp.fit.enums.ShareType;

public class Context {
    public String country;
    public String market;
    public String share;
    public ShareType shareType;

    public Context(){

    }

    public Context(String country, String market, String share, ShareType shareType) {
        this.country = country;
        this.market = market;
        this.share = share;
        this.shareType = shareType;
    }

    public boolean isMarket(){
        return market != null;
    }

    public boolean isCountry(){
        return country != null;
    }
}
