package model;

import java.util.Date;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Whisky whisky)) return false;
        return Objects.equals(getWhiskyDato(), whisky.getWhiskyDato()) && getKvalitet() == whisky.getKvalitet() && Objects.equals(getFyld(), whisky.getFyld());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWhiskyDato(), getKvalitet(), getFyld());
    }
}
