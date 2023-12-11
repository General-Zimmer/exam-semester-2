package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("SpellCheckingInspection")
public class Whisky implements Serializable {
    private final LocalDate whiskyDato;
    private final Kvalitet kvalitet; // SINGLECASK, SINGLEMALT, BLENDED
    private final FadIndhold fadIndhold;
    private final float mængde;

    /**
     *  Constructor for Whisky
     * @param whiskyDato Datoen for den dag destilleringen blev til whisky
     * @param kvalitet Kvaliteten af whiskyen(SINGLECASK, SINGLEMALT, BLENDED)
     * @param fadIndhold Er en opfyldning af et fad. Opfyldningen kan indeholde flere destillater og kan skifte fad over tid
     */
    public Whisky(LocalDate whiskyDato, Kvalitet kvalitet, FadIndhold fadIndhold, float mængde) {
        this.whiskyDato = whiskyDato;
        this.kvalitet = kvalitet;
        this.fadIndhold = fadIndhold;
        this.mængde = mængde;
    }

    public String getKompleteHistorie() {

        LocalDate ældsteDestillat = LocalDate.MAX;
        for (Fyld fyld : fadIndhold.getFyld()) {
            for (Destillat destillat : fyld.getDestillater().keySet()) {
                if (destillat.getDestillationsDato().isBefore(ældsteDestillat)) {
                    ældsteDestillat = destillat.getDestillationsDato();
                }
            }
        }



        String kompletHistorie = "Whiskyens historie: \n" +
                "Whiskyen er blevet produceret d. " + whiskyDato + ". Den er lavet som en: "+ kvalitet + ". og har en samlet mængde på "+ mængde + "\n " +
                "Det ældste destillatet for whiskyen er lavet d. " + ældsteDestillat + " og blev destilleret af " ;

        return kompletHistorie;
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

    public FadIndhold getBlanding() {
        return fadIndhold;
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
