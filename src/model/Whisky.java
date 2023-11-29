package model;

import java.util.Date;
import java.util.Objects;

public class Whisky {
    private final Date whiskyDato;
    private final Kvalitet kvalitet; // SINGLECASK, SINGLEMALT, BLENDED
    private final Fyld fyld;

    /**
     *  Constructor for Whisky
     * @param whiskyDato Datoen for den dag destilleringen blev til whisky
     * @param kvalitet Kvaliteten af whiskyen(SINGLECASK, SINGLEMALT, BLENDED)
     * @param fyld Er en opfyldning af et fad. Opfyldningen kan indeholde flere destillater og kan skifte fad over tid
     */
    public Whisky(Date whiskyDato, Kvalitet kvalitet, Fyld fyld) {
        this.whiskyDato = whiskyDato;
        this.kvalitet = kvalitet;
        this.fyld = fyld;
    }

    /**
     * Getter for whiskyDato
     * @return Date
     */
    public Date getWhiskyDato() {
        return whiskyDato;
    }

    /**
     * Getter for kvalitet
     * @return Kvalitet
     */
    public Kvalitet getKvalitet() {
        return kvalitet;
    }

    /**
     * Getter for fyld
     * @return Fyld
     */
    public Fyld getFyld() {
        return fyld;
    }

    /**
     * Equals metode til at sammenligne to Whisky objekter
     * @param o Objektet der skal sammenlignes med
     * @return Om de er ens eller ej
     */
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
