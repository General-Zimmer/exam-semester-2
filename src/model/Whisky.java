package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("SpellCheckingInspection")
public class Whisky implements Serializable {
    private final LocalDate whiskyDato;
    private final Kvalitet kvalitet; // SINGLECASK, SINGLEMALT, BLENDED
    private final Blanding blanding;
    private final float mængde;

    /**
     *  Constructor for Whisky
     * @param whiskyDato Datoen for den dag destilleringen blev til whisky
     * @param kvalitet Kvaliteten af whiskyen(SINGLECASK, SINGLEMALT, BLENDED)
     * @param blanding Er en opfyldning af et fad. Opfyldningen kan indeholde flere destillater og kan skifte fad over tid
     */
    public Whisky(LocalDate whiskyDato, Kvalitet kvalitet, Blanding blanding, float mængde) {
        this.whiskyDato = whiskyDato;
        this.kvalitet = kvalitet;
        this.blanding = blanding;
        this.mængde = mængde;
    }

    /**
     * Getter for whiskyDato
     * @return Date
     */
    public LocalDate getWhiskyDato() {
        return whiskyDato;
    }

    /**
     * Getter for kvalitet
     * @return Kvalitet
     */
    public Kvalitet getKvalitet() {
        return kvalitet;
    }

    public Blanding getBlanding() {
        return blanding;
    }

    public float getMængde() {
        return mængde;
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
        return Float.compare(mængde, whisky.mængde) == 0 && Objects.equals(getWhiskyDato(), whisky.getWhiskyDato()) && getKvalitet() == whisky.getKvalitet() && Objects.equals(getBlanding(), whisky.getBlanding());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWhiskyDato(), getKvalitet(), getBlanding(), mængde);
    }
}
