package eu.forapp.fit.pojo;

import eu.forapp.fit.entity.Share;

public class Effect {
    private final Share share;
    private final Twin twin;

    public Effect(Share share, Twin twin) {
        this.share = share;
        this.twin = twin;
    }

    public Share getShare() {
        return share;
    }

    public Twin getTwin() {
        return twin;
    }
}
