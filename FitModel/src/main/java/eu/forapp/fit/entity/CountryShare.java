package eu.forapp.fit.entity;

import eu.forapp.exception.ForAppException;
import eu.forapp.fit.enums.ShareType;

public class CountryShare extends Share {
    private Country country;

    public CountryShare(){
    }

    public CountryShare(String name, ShareType shareType) throws ForAppException {
        super(name, shareType);
    }

    public Country getCountry(){
        return country;
    }

    void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("[");
        sb.append(getName());
        sb.append(":");
        sb.append(country.getNick());
        sb.append(", ");
        sb.append(getShareType().toString());
        sb.append("]");
        return sb.toString();
    }

}
