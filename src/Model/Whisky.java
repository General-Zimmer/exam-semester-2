package Model;

import java.util.Date;

public class Whisky {
    private final Date whiskyDato;
    private final Kvalitet kvalitet; // SINGLECASK, SINGLEMALT, BLENDED
    private final Fyld fyld;

    public Whisky(Date whiskyDato, Kvalitet kvalitet, Fyld fyld) {
        this.whiskyDato = whiskyDato;
        this.kvalitet = kvalitet;
        this.fyld = fyld;
    }

    public Date getWhiskyDato() {
        return whiskyDato;
    }

    public Kvalitet getKvalitet() {
        return kvalitet;
    }

    public Fyld getFyld() {
        return fyld;
    }
}
